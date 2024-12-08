# TV Series Nudity Checker & Rating Retriever
**Current Browser Support**: This app currently works with Firefox only. In the future, support for additional browsers will be added.

This Java app allows you to search for a TV series and check if its episodes contain nudity, as well as retrieve the series' rating charts. You can also enable debug mode for more detailed information.

## Step 1: Enter the Series
Specify the name of the series you want to search for:
[series]
You can add `-debug` to enable debug mode for more detailed information:
[series] -debug

## Step 2: Choose an Action
Once the series is entered, the app will give you several options. You can choose to:

### Nudity Checker (`nc` or `NC`):
Checks if each episode contains nudity.
- `nc` or `NC`: Runs the nudity check without debug info.
- `nc -debug` or `NC -debug`: Runs the nudity check with debug information.

### Rating Chart Retriever (`a` or `A`):
Retrieves the rating charts for the series.
- `a` or `A`: Retrieves ratings without debug info.
- `a -debug` or `A -debug`: Retrieves ratings with debug information.

### Search for Another Series (`c` or `C`):
Allows you to search for another series.

### Exit the App (`x` or `X`):
Exits the application.

## Notes:
- **Error Handling**: If the series is not found, the app will prompt you to try again or exit.
- **Debug Mode**: Adding `-debug` to any command provides additional logging and details during task execution.

## Author Information:
- **Author**: MYST
- **Version**: 1.0
