// com_wander_jni_JNITest.c  
  
#include "com_wander_jni_JNITest.h"  
  
#ifdef __cplusplus  
extern "C"  
{  
#endif   
/*
 * Class:     com_wander_jni_JNITest
 * Method:    test
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wander_jni_JNITest_test(  
        JNIEnv *env, jclass cls, jstring j_str) 
{  
    const char *c_str = NULL;  
    char buff[128] = { 0 };  
    c_str = (*env)->GetStringUTFChars(env, j_str, NULL);  
    if (c_str == NULL)  
    {  
        printf("out of memory.\n");  
        return NULL;  
    }  
    printf("Hello %s in c\n", c_str);  
    sprintf(buff, "return %s", c_str);  
    (*env)->ReleaseStringUTFChars(env, j_str, c_str);  
    return (*env)->NewStringUTF(env, buff);  
}  
#ifdef __cplusplus  
}  
#endif