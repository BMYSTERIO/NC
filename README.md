# NC
This app performs a **general content check** for TV series, including:
- Nudity
- Violence
- Alcohol, Drugs & Smoking
- Frightening & Intense Scenes
- Profanity

It checks all episodes of a series and outputs the results in a folder structure. A main folder is created with the series name, and inside that folder, there are subfolders for each category (e.g., Nudity, Violence, etc.), containing the results for each episode, average speed of 100 ep in 20 min.

It is recommended to use **Firefox** for faster performance.

## How to Use:
1. **Step 1**: Enter the Series
   - Type the name of the series you want to search for.
     Example: `[series]`
   - Optionally, add `-debug` to enable debug mode for more detailed info.
     Example: `[series] -debug`
   
2. **Step 2**: Choose an Action
   - General Content Checker (nc or NC): Performs a general check for nudity, violence, alcohol, drugs & smoking, frightening & intense scenes, and profanity.
     - `nc`: Runs the general content check without debug info.
     - `nc -debug`: Runs the general content check with debug info.
   - Rating Chart Retriever (a or A): Retrieves the rating charts for the series.
     - `a`: Retrieves ratings without debug info.
     - `a -debug`: Retrieves ratings with debug info.
   - Search for another series (c): Allows you to search for another series.
   - Exit the app (x): Exits the application.
  


## IMPORTANT NOTE:
If the app does not retrieve proper data for the series, it may output incomplete or incorrect results, such as saving profanity in the violence category, etc. This happens because the app relies on the available data for accurate categorization. If no data is retrieved, it means that **no data is available for that series**, and the app cannot process the content properly.

**Make sure the series has data available to ensure accurate results


## Notes:
- **Error Handling**: If the series is not found, the app will prompt you to try again or exit.
- **Debug Mode**: Add `-debug` to any command to receive additional logs.
- **Performance**: While the app supports multiple browsers, it is recommended to use Firefox for faster execution.

## Author:
This app was developed by **MYST**.
