package net.marcoromano.parcelable

// Taken from: https://stackoverflow.com/questions/70916976/how-to-use-parcelize-annotation-in-shared-module-of-kotlin-multiplatform-projec
// This won't work with K2 due to: https://youtrack.jetbrains.com/issue/KT-58892

//@OptIn(ExperimentalMultiplatform::class)
//@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
public expect annotation class CommonParcelize()

public expect interface CommonParcelable
