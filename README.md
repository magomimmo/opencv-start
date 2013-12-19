# opencv-start

A Leiningen template for creating OpenCV project.

## Prerequisites

The `opencv-start` lein template requires you already installed into
your local maven repository both the `opencv/opencv "2.4.7"` and
`opencv/opencv-native "2.4.7"` artifacts.

To satisfy these requirements follow the
[Introduction to OpenCV Development with Clojure][1] available on the
official [opencv.org][2] web site.

### Quick start

The following is a very quick start guide to install **OpenCV 2.4.7 or
higher** which includes Java bindings. It assumes that you already
installed the requirements to build OpenCV lib from the source.

#### Install OpenCV 2.4 with java bindings

I assume you're going to install opencv in the `~/dev` directory.

```bash
cd ~/dev
git clone git://github.com/Itseez/opencv.git
cd opencv
git checkout 2.4
mkdir build
cd build
cmake -DBUILD_SHARED_LIBS=OFF ..
make -j8
```

#### Install Leiningen

The available [Leiningen installation guide][3] is very easy to be followed:

1. Download the [script][4]
2. Place it on your $PATH (cf. ~/bin is a good choice if it is on your path.)
3. Set the script to be executable. (i.e. chmod 755 ~/bin/lein).

If you work on Windows, follow this [instruction][5].

#### Install the lein-localrepo plugin

If it does not exist yet, create the `.lein` directory in your home
directory.

```bash
mkdir -p ~/.lein
```

Create a new file named `profiles.clj` in the `~/.lein` directory with
the following content:

```clojure
{:user {:plugins [[lein-localrepo "0.5.2"]]}}
```

#### Install opencv libs as local maven repository artifacts

Note that this step has to be done one time only for each machine you
want to run an OpenCV project in Clojure.

##### Preparation

Start by creating a new directory of your choice
(e.g. `~/dev/clj-opencv`) and by copying in it the `opencv-247.jar`
file generated when you previously built opencv with java bindings.

```bash
mkdir -p ~/dev/clj-opencv
cd ~/dev/clj-opencv
cp ~/dev/opencv/build/bin/opencv-247.jar .
```

To be able to add the shared native lib to the local maven repository,
we first need to package it as a jar file.

The native lib has to be copied into a directories layout which mimics
the names of your operating system and architecture. I’m using a Mac
OS X with a X86 64 bit architecture. So my layout will be the
following:

```bash
# for Mac OS X
mkdir -p native/macosx/x86_64
# for GNU/Linux
# mkdir -p native/linux/x86_64 
```

Copy into it the shared native lib for your OS (e.g.
`libopencv_java247.dylib` for Mac OS X, `libopencv_java247.so` for
GNU/Linux)

```bash
# for Mac OS X
cp ~/dev/opencv/build/lib/libopencv_java247.dylib native/macosx/x86_64/
# for GNU/Linux
# cp ~/dev/opencv/build/lib/libopencv_java247.so native/linux/x86_64/
```

If you’re running OpenCV from a different OS/Architecture pair, here
is a summary of the mapping you can choose from.

```
OS

Mac OS X -> macosx
Windows  -> windows
Linux    -> linux
SunOS    -> solaris

Architectures

amd64    -> x86_64
x86_64   -> x86_64
x86      -> x86
i386     -> x86
arm      -> arm
sparc    -> sparc
```

You now need to package the native lib in a jar file by using the jar
command.

```bash
jar -cMf opencv-native-247.jar native
```

##### Install into the local maven repository

First install the `opencv-247.jar` in the local maven repository.

```bash
lein localrepo install opencv-247.jar opencv/opencv 2.4.7
```

The above command installs in the local maven repository
(e.g. `~/.m2/repository/`) the `opencv-247.jar` jar file as the
`2.4.7` release of the `opencv` artifactId in the `opencv` groupId.

Next install the shared native opencv lib too.

```bash
lein localrepo install opencv-native-247.jar opencv/opencv-native 2.4.7
```

The above command installs in the local maven repository the `2.4.7`
release of the `opencv-native` artifactId in the `opencv` groupId.

You're now ready to use the `opencv-start` lein template to create new
Clojure projects interoperating with the `opencv` lib.

## Usage

To create a new OpenCV project issue the following `lein` command
(task in leiningen parlance):

```bash
lein new opencv-start com.sinapsi/first-project --snapshot
```

> NOTE 1: the `opencv-start` lein template has still to be published on
> clojars. At the moment the only way to use it is by `git clone`,
> locally install and use its snaphost release.
> 
> ```bash
> cd ~/dev
> git clone https://github.com/magomimmo/opencv-start.git
> cd opencv-start
> lein install
> ```

## License

Copyright © 2013 Giacomo (Mimmo) Cosenza aka Magomimmo

Distributed under the BSD 3-clause

[1]: http://docs.opencv.org/2.4/doc/tutorials/introduction/clojure_dev_intro/clojure_dev_intro.html
[2]: http://opencv.org/
[3]: https://github.com/technomancy/leiningen#installation
[4]: https://raw.github.com/technomancy/leiningen/stable/bin/lein
[5]: https://github.com/technomancy/leiningen#windows
