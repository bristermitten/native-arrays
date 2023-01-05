#include "UnsafeMemory.h"
#include <jni.h>
#include <iostream>

jfieldID getAddressField(JNIEnv *env, jobject o) {
    return env->GetFieldID(
            env->GetObjectClass(o),
            "address",
            "J"
    );
}


JNIEXPORT jlong JNICALL Java_UnsafeMemory_allocate(JNIEnv *, jclass, jlong len) {
    auto arr = new jobject[len];
    return (jlong) arr;
}

JNIEXPORT void JNICALL Java_UnsafeMemory_free(JNIEnv *env, jobject mm, jlong size) {
    auto theArr = (jobject *) env->GetLongField(mm, getAddressField(env, mm));
    for (int i = 0; i < size; ++i) {
        env->DeleteGlobalRef(theArr[i]);
    }
    delete theArr;
}


JNIEXPORT jobject JNICALL Java_UnsafeMemory_get(JNIEnv *env, jobject o, jlong idx) {
    auto theArray = (jobject *) env->GetLongField(o, getAddressField(env, o));
    return theArray[idx];
}

JNIEXPORT void JNICALL Java_UnsafeMemory_set(JNIEnv *env, jobject o, jlong idx, jobject val) {
    auto theArray = (jobject *) env->GetLongField(o, getAddressField(env, o));
    jobject newRef = env->NewGlobalRef(val);
    theArray[idx] = newRef;
}