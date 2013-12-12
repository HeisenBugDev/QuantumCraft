# Gradle forge instructons

Install gradle

Open up a terminal, go cd into the QC dir, run this command: `gradle setupDecompWorkspace
ideaModule`

Now open up intellij and go to _File> Import Project_. Select the build.gradle file for import. Once the project is open, click JetGradle on the right and then tasks at the top. Run genIntellijRuns.Then in both Client and Server change the working directory to `./mcrun`

Next, go to `Project Settings > modules> QC` add in bcdobf if it's not there and if it is make sure it has scope set to compile.
