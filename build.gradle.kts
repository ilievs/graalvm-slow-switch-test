plugins {
    id("java")
    id("application")
    id("com.google.protobuf") version "0.9.4"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("org.graalvm.buildtools.native") version "0.10.1"
}

group = "com.dreamlabs.osm"
version = "0.1.0"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.dreamlabs.osm.pbf.Main"
}

graalvmNative {
    toolchainDetection.set(false)
    binaries {
        all {
            javaLauncher = javaToolchains.launcherFor {
                languageVersion = JavaLanguageVersion.of(21)
                vendor = JvmVendorSpec.BELLSOFT
            }
        }
        named("main") {
            // TODO consider adding a maximum heap size during build
            //  with -R:MaxHeapSize OR when running the binary with
            //  -Xmx, e.g. -Xmx64m for 64MB
            buildArgs.addAll("-O3", "--strict-image-heap")
        }

    }
}

val protoVer = "4.26.0"
val rxJavaVer = "3.1.8"
val junitVer = "5.10.2"

dependencies {
    implementation("com.google.protobuf:protobuf-java:$protoVer")
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVer")

    implementation("org.eclipse.collections:eclipse-collections-api:11.1.0")
    implementation("org.eclipse.collections:eclipse-collections:11.1.0")

    testImplementation(platform("org.junit:junit-bom:$junitVer"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:$protoVer"
    }
}

tasks.test {
    useJUnitPlatform()
}
