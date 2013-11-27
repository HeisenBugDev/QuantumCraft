# Gradle forge instructons

Gradle forge is very complicated to setup but easier to work with that ant scripts and the pahimar setup. Let's begin on what you need to do!

First, go here: http://files.minecraftforge.net/ and download #964. Next, clone the repo somewhere (you're going to want to start fresh). Then extract the contents of that to the QC dir.

Before qc dir:
 - src
 - other stuff
 - bacon.txt
 - readme
 - derp

after:
 - src
 - forge stuff
 - bacon
 - other stuff

Now here comes the fun part. Make sure you have gradle installed! There is a wrapper but that doesn't seem to be working. Open up a terminal, go cd into the QC dir, run this command: `gradle setupDevWorkspace`

Now open up intellij and go to _File> Import Project_. Select the build.gradle file for import. Once the project is open, click JetGradle on the right and then tasks at the top. Run genIntellijRuns. Then configure your runs and change VM Options in server to `-Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true -Djava.library.path="../../build/natives"` Then in both Client and Server change the working directory to `/Volumes/MacStorage/Code/QC/mcrun`

Next, go to `Project Settings > modules> QC` add in bcdobf if it's not there and if it is make sure it has scope set to compile.
