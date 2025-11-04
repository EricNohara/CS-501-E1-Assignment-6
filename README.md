# CS 501 E1 Assignment 6 AI Use Documentation

## Eric Nohara-LeClair

# What's for Dinner

### Where did I use AI?

I used AI to help generate sample data for the recipes to display within the app. I also used AI to get the suggested structure for the app. I used class notes to implement the navigation, with some help from AI to debug issues I had along the way.

### How did I use AI?

I used AI more as a teaching tool here, teaching myself how to best structure and implement navigation. I also used it to reduce the time taken to implement by generating sample data instead of coming up with recipes myself.

# My Daily Hub

### Where did I use AI?

I used AI mainly for debugging purposes here. I used the structure from the previous app to implement this one, so I did not need as much help from external sources. I did use AI to help debug an issue I had with dependencies and imports not recognizing certain functions.

### How did I use AI?

I used AI as a debugging tool to help me parse through error messages and filter out what could be wrong in my code without having to do extensive testing.

# Explore Boston

### Where did I use AI?

I used AI mainly to help debug an issue I had with the navigation, where the category screen would render but when I clicked a category, the lambda function which was supposed to navigate to the list of items within the category was still navigating to the details page.

### How did I use AI?

I used logging to pinpoint the issue. I then inputted those logs along with snippets of code and a description of the bug into ChatGPT. I then followed their suggestion of wrapping the NavHost in a key() to ensure that it rerenders the components every time the back stack is updated, meaning that the issue of the lambda callbacks not updating between renders would be fixed. After doing so, everything worked as intended.

# Where did AI Misunderstand Navigation

In my experience, AI did not really misunderstand navigation, although I did not use it extensively to implement that part of the apps. If anything, AI misunderstood my prompts, meaning it was my fault that it did not understand exactly what to do. But when I asked it to give me an example of navigation, it gave me a clear idea of how to structure and implement navigation within my own apps.
