# EspressoSpoonStructure

If you need to configure Espresso & Spoon from scratch, follow this link to learn how to do it:
https://github.com/emmasuzuki/EspressoSpoonDemo

In this project, we will be implementing the following solutions:
- ScreenObject pattern
- JUnit4 
- Spoon

About ScreenObject pattern:

Introduction: 
Your app has areas that your tests need to interact with. A Screen Object models these as object within the test code. 
This reduces the amount of duplicated code and means that if the UI changes, the fix need only be applied in one place. 

From a development perspective, screen objects represent the services offered by a particular page. It's the only place where
the mechanics of your app will be defined. The screen object will isolate that implementation so tests won't have to worry
about how things are doing. Long story short, screen objects "know" the mechanics of your app and tests use them.

Screen Objects don't actually need to represent an entire screen. It might represent just a feature that appears many times
in your app (think in notifications or a header/footer).

Summary:
- The public methods represent the services that the screen offers.
- Try not to expose the internals of the screen.
- Generally don't make assertions.
- Need not represent an entire screen. 
