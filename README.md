# NC
This app checks if each episode of a TV series contains nudity by retrieving descriptions and images, and it also fetches the rating charts for the series. Users can perform these tasks by specifying the series and action, with an optional debug mode for extra information.

Step 1: Enter the Series
Specify the Series:
Enter the name of the series you want to search for.
Example:
[series]
Or, you can add -debug to enable debug mode for more detailed information.
Example:
[series] -debug
Step 2: Choose an Action
Once the series is entered, the app will give you several options. You can choose to:

Nudity Checker (nc or NC):
Checks if each episode contains nudity.

nc or NC: Runs the nudity check without debug info.
nc -debug or NC -debug: Runs the nudity check with debug information.
Rating Chart Retriever (a or A):
Retrieves the rating charts for the series.

a or A: Retrieves ratings without debug info.
a -debug or A -debug: Retrieves ratings with debug information.
Search for another series (c or C):
Allows you to search for another series.

Exit the app (x or X):
Exits the application.

Example Usage:
To check nudity for [series]:

[series] nc
[series] nc -debug (with debug mode)
To get ratings for [series]:

[series] a
[series] a -debug (with debug mode)
To check nudity for [series] and get ratings:

[series] nc a
To search for another series:

[series] c
To exit the app:

x
Notes:
Error Handling: If the series is not found, the app will prompt you to try again or exit.
Debug Mode: Adding -debug to any command provides additional logging and details during task execution.
