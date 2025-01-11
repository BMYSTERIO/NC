//START IMPORT-SECTION
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import org.openqa.selenium.chrome.ChromeOptions;

public class run {


     //START COLORS
    public static final String RESET = "\033[0m";  // Reset to default color
    public static final String RED = "\033[0;31m"; // Red
    public static final String GREEN = "\033[0;32m"; // Green
    public static final String YELLOW = "\033[0;33m"; // Yellow
    public static final String BLUE = "\033[0;34m"; // Blue
    public static final String LIGHT_RED = "\033[1;31m"; // Light Red (can appear somewhat orange)
    public static final String BRIGHT_YELLOW = "\033[1;33m"; // Bright Yellow (can also look like orange)
    public static final String MAGENTA = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String BLACK = "\033[0;37m";
    //END COLORS
 
    //START OUTPUTS
    public static void outputImdb(String Content){
        System.out.println( YELLOW + "["+BLACK+"IMDB"+YELLOW+"] " + RESET + Content);
    }
    public static void outputSavingpic(String Content){
        System.out.println( CYAN + "[DOWNLOAD] " + RESET + Content);
    }
    public static void outputGoogle(String Content){
        System.out.println("["+BLUE+"G"+RED+"O"+YELLOW+"O"+BLUE+"G"+GREEN+"L"+RED+"E"+RESET+"] " + RESET + Content);
    }
    public static void outputTvcharts(String Content){
        System.out.println( BLUE + "[TVCHARTS] " + RESET + Content);
    }
    public static void outputDone(Long seconds){
        System.out.println( BLACK + "[NC] " + RESET + GREEN + seconds  + RESET + " Seconds");
    }
    public static void outputNc(String Content){
        System.out.println( BLACK + "[NC] " + RESET + Content);
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //END OUTPUTS


    public static void App(WebDriver driver){

        //START METHOD TO NOT OUTPUTS ERRORS
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        //END METHOD TO NOT OUTPUTS ERRORS



        //START DECLRATIONS
        Scanner in = new Scanner(System.in);
        String rateSeries;
        //END DECLRATIONS




        //START TRY 1 
        try {


            //START WHILE 1
            while (true) {


                //START INTRO
                System.out.println(BLACK+" ----              "+YELLOW+"  NC v1.2"+run.CYAN+"     "+BLACK+"                 ----"+RESET);
                System.out.println(BLACK+"----          Write the series needed              ----");
                System.out.println(" ----                By: "+BLUE+"MYST"+BLACK+"                     ----"+RESET);
                System.out.print(BLACK+"Search: "+RESET);
                String series = in.nextLine();
                if (series.equals("/")||series.equals("/")) {
                    ncv2.space(RED+"Exiting..."+RESET);
                    driver.close();
                    return;
                }
                if (series.equals("-help")) {
                    System.out.println(run.BLUE + "Help Page" + RESET);
                    System.out.println(run.CYAN + "This is the NC v1.2 ! Here's how to use the program:" + RESET);
                    System.out.println(run.YELLOW + "1. Search for a series:" + RESET);
                    System.out.println(run.GREEN + "   - Type the name of the series to check if it contains nudity and view its rating." + RESET);
                    System.out.println(run.YELLOW + "2. Commands:" + RESET);
                    System.out.println(run.GREEN + "   - (a) Get Ratings: Fetches the ratings of the series." + RESET);
                    System.out.println(run.GREEN + "   - (nc) Nudity Checker: Checks if the series has nudity." + RESET);
                    System.out.println(run.GREEN + "   - (c) Search for another series: Returns to search prompt." + RESET);
                    System.out.println(run.RED + "   - (x) Exit: Exits the program." + RESET);
                    System.out.println(run.CYAN + "3. Debug Mode:" + RESET);
                    System.out.println(run.GREEN + "   - Use '-debug' after the series name to enter debug mode for advanced information." + RESET);
                    System.out.println(run.BLUE + "Search: [series] -[options]" + RESET);
                    continue;  // Skip the rest of the loop after showing help
                }
                String[] seriesParts = series.split("-");
                //END INTRO


                
                //START IF-SERIES-NO-COMMAND
                if (seriesParts.length == 1) {

                    //START OUTERLOOP FOR IF-SERIES-NO-COMMAND
                    outerLoop:
                    while (true) {

                        //START CONNECTING FOR IF-SERIES-NO-COMMAND
                        try {
                            String testInternet = ncv2.testInternet(driver); 
                            if (testInternet.equals("end")) {
                                ncv2.space("Exiting...");
                                driver.close();
                                return;
                            }   
                        //END CONNECTING FOR IF-SERIES-NO-COMMAND
                    


                        //START HEADER FOR IF-SERIES-NO-COMMAND    
                        ArrayList<String> header = ncv2.header(series,driver); 
                        while (true) {
                        double rateDoub = Double.parseDouble(header.get(3));
                            if (rateDoub > 9) {
                                rateSeries = run.GREEN + rateDoub;
                            } else if (rateDoub > 8.5 && rateDoub < 9) {
                                rateSeries = run.BRIGHT_YELLOW + rateDoub;
                            } else if (rateDoub > 7.5 && rateDoub < 8.5) {
                                rateSeries = run.YELLOW + rateDoub;
                            } else {
                                rateSeries = run.LIGHT_RED + rateDoub;
                            }
                        series = header.get(0);    
                        if (header.get(2).equals("no")) {
                            System.out.println(run.BLUE + "Nudity Checker " + BLACK + "(nc)                                  |            " +run.MAGENTA+header.get(0) + RESET + run.BLUE +  "\nGet Ratings " + BLACK + "(a)                                      |            " + rateSeries+run.RESET+"/"+run.BLUE+"10"+ run.RESET + run.BLUE + "\nSearch for another series " + BLACK + "(c)                        |            " + run.BLUE+header.get(1)+ run.RESET +  run.BLUE + "\nExit " + BLACK + "(x)                                             |" + RESET);
                            System.out.print(":");
                        }else{
                        System.out.println(run.BLUE + "Nudity Checker " + BLACK + "(nc)                                  |            " +run.MAGENTA+header.get(0) + RESET +run.BLUE +  "\nGet Ratings " + BLACK + "(a)                                      |            " + rateSeries+run.RESET+"/"+run.BLUE+"10"+ run.RESET + run.BLUE + "\nSearch for another series " + BLACK + "(c)                        |           " + run.BLUE+header.get(1) + run.BLACK +"-" +run.BLUE+header.get(2)+ run.RESET + run.BLUE +  "\nExit " + BLACK + "(x)                                             |" + RESET);
                        System.out.print(":");
                        }
                        //END HEADER FOR IF-SERIES-NO-COMMAND
                        


                        //START CHOICE FOR IF-SERIES-NO-COMMAND
                        String choice = in.nextLine().trim();
                        String[] choiceParts = choice.split("-");
                        //START IF-CHOICE-COMMAND FOR IF-SERIES-NO-COMMAND
                        if (choiceParts.length>1) {
                            if (choiceParts[0].trim().equals("nc") ||choiceParts[0].trim().equals("NC") ) {
                                if (choiceParts[1].trim().equals("debug")) {
                                    ncv2.ncO(series,driver);
                                }
                                else{
                                    ncv2.space(RED + " ".repeat(33) + "Unknown command!" + RESET);
                                }
                            }else if (choiceParts[0].trim().equals("A") ||choiceParts[0].trim().equals("a")) {
                                if (choiceParts[1].trim().equals("debug")) {
                                    //rate.RatesGetterO(series);
                                }
                                else{
                                    ncv2.space(RED + " ".repeat(33) + "Unknown command!" + RESET);
                                }
                            }else{
                                ncv2.space(RED + " ".repeat(33) + "Invaild input!" + RESET);
                            }
                        //END IF-CHOICE-COMMAND FOR IF-SERIES-NO-COMMAND





                        //START IF-CHOICE-NO-COMMAND FOR IF-SERIES-NO-COMMAND
                        }else if (choiceParts.length == 1) {
                            if (choice.equals("x") || choice.equals("X")) {
                                ncv2.space(RED+"Exiting..."+RESET);
                                driver.close();
                                return;
                            }else if (choice.equals("c") || choice.equals("C")) {
                                ncv2.space("");
                                break outerLoop;
                            }else if (choice.equals("a") || choice.equals("A")) {
                                //rate.RatesGetter(series);
                                ncv2.space("");
                            }else if (choice.equals("nc") || choice.equals("NC")) {
                                ncv2.nc(series,driver);
                                ncv2.space("");
                            }else{
                                ncv2.space(RED + " ".repeat(33) + "Invaild input!" + RESET);
                            }
                        }

                    }
                //END IF-CHOICE-NO-COMMAND FOR IF-SERIES-NO-COMMAND


                //START CATCH FOR SERIES-NO-COMMAND
                }catch (Exception e){
                        System.out.println(RED + "Couldnt find " + RESET + YELLOW + series + RESET);
                        System.out.println(BLACK+"Try Again ("+YELLOW+"y"+BLACK+"/"+RED+"n"+BLACK+")\nSearch for another series ("+BLUE+"c"+BLACK+")"+RESET);
                        String yorno = in.nextLine();
                        if (yorno.equals("y") || yorno.equals("Y")) {
                            ncv2.space("");
                        } else if (yorno.equals("n") || yorno.equals("N")) {
                            ncv2.space(RED + "Exiting..." + RESET);
                            driver.close();
                            return;
                        }else if (yorno.equals("c") || yorno.equals("C")) {
                            break outerLoop;
                        } else {
                            while (true) {
                                ncv2.space("");
                                System.out.println(RED + "Invalid input!" + RESET);
                                yorno = in.nextLine();
                                if (yorno.equals("y") || yorno.equals("Y")) {
                                    ncv2.space("");
                                    break;
                                } else if (yorno.equals("n") || yorno.equals("N")) {
                                    ncv2.space(RED + "Exiting..." + RESET);
                                    driver.close();
                                    return;
                                }
                            }
                        }
                    
                }
                //END CATCH FOR SERIES-NO-COMMAND








                }
            //END OUTERLOOP FOR IF-SERIES-NO-COMMAND
            

            
            }
            //END IF-SERIES-NO-COMMAND


            //START IF-SERIES-COMMAND
                else if (seriesParts[1].equals("debug")) {
                    //START OUTERLOOP FOR IF-SERIES-COMMAND
                    outerLoop:
                    while (true) {
                        
                        try {



                        //START CONNECTION FOR IF-SERIES-COMMNAD
                            String testInternet = ncv2.testInternetO(driver);
                            if (testInternet.equals("end")) {
                                ncv2.space("Exiting...");
                                driver.close();
                                return;
                            }
                        //END CONNECTION FOR IF-SERIES-COMMAND


                        //START HEADER FOR IF-SERIES-COMMAND
                            ArrayList<String> header = ncv2.headerO(seriesParts[0],driver); 
                            seriesParts[0] = header.get(0);
                            while (true) {
                                double rateDoub = Double.parseDouble(header.get(3));
    
                                if (rateDoub > 9) {
                                    rateSeries = run.GREEN + rateDoub;
                                } else if (rateDoub > 8.5 && rateDoub < 9) {
                                    rateSeries = run.BRIGHT_YELLOW + rateDoub;
                                } else if (rateDoub > 7.5 && rateDoub < 8.5) {
                                    rateSeries = run.YELLOW + rateDoub;
                                } else {
                                    rateSeries = run.LIGHT_RED + rateDoub;
                                }
                            series = header.get(0);        
                            if (header.get(2).equals("no")) {
                                System.out.println(run.BLUE + "Nudity Checker " + BLACK + "(nc)                                  |            " +run.MAGENTA+header.get(0) + RESET + run.BLUE + "\nGet Ratings " + BLACK + "(a)                                       |            " + rateSeries+run.RESET+"/"+run.BLUE+"10"+ run.RESET + run.BLUE + "\nSearch for another series " + BLACK + "(c)                        |            " + run.BLUE+header.get(1)+ run.RESET +  run.BLUE + "\nExit " + BLACK + "(x)                                             |" + RESET);
                                System.out.print(":");
                            }else{
                            System.out.println(run.BLUE + "Nudity Checker " + BLACK + "(nc)                                  |            " +run.MAGENTA+header.get(0) + RESET + run.BLUE + "\nGet Ratings " + BLACK + "(a)                                      |            " + rateSeries+run.RESET+"/"+run.BLUE+"10"+ run.RESET + run.BLUE + "\nSearch for another series " + BLACK + "(c)                        |          " + run.BLUE+header.get(1) + run.RESET +run.BLACK+"-" +run.BLUE+header.get(2)+ run.RESET +  run.BLUE + "\nExit " + BLACK + "(x)                                             |" + RESET);
                            System.out.print(":");
                            }
                        //END HEADER FOR IF-SERIES-COMMAND



                        //START CHOICE FOR IF-SERIES-COMMAND
                        String choice = in.nextLine().trim();
                        String[] choiceParts = choice.split("-");

                        
                        
                        //START CHOICE-COMMAND FOR IF-SERIES-COMMAND
                        if (choiceParts.length>1) {
                            if (choiceParts[0].trim().equals("nc") ||choiceParts[0].trim().equals("NC") ) {
                                if (choiceParts[1].trim().equals("debug")) {
                                    ncv2.ncO(seriesParts[0],driver);
                                }
                                else{
                                    ncv2.space(RED + " ".repeat(33) + "Unknown command!" + RESET);
                                }
                            }else if (choiceParts[0].trim().equals("A") ||choiceParts[0].trim().equals("a")) {
                                if (choiceParts[1].trim().equals("debug")) {
                                    //rate.RatesGetterO(seriesParts[0]);
                                }
                                else{
                                    ncv2.space(RED + " ".repeat(33) + "Unknown command!" + RESET);
                                }
                            }else{
                                ncv2.space(RED + " ".repeat(33) + "Invaild input!" + RESET);
                            }
                        }
                        //END CHOICE-COMMAND FOR IF-SERIES-COMMAND
                        


                        //START CHOICE-NO-COMMAND FOR IF-SERIES-COMMAND
                        else if (choiceParts.length == 1) {
                            if (choice.equals("x") || choice.equals("X")) {
                                ncv2.space(RED+"Exiting..."+RESET);
                                driver.close();
                                return;
                            }else if (choice.equals("c") || choice.equals("C")) {
                                ncv2.space("");
                                break outerLoop;
                            }else if (choice.equals("a") || choice.equals("A")) {
                               // rate.RatesGetter(series);
                                ncv2.space("");
                            }else if (choice.equals("nc") || choice.equals("NC")) {
                                ncv2.nc(series,driver);
                                ncv2.space("");
                            }else{
                                ncv2.space(RED + " ".repeat(33) + "Invaild input!" + RESET);
                            }
                        }
                        //END CHOICE-NO-COMMAND FOR IF-SERIES-COMMAND
    
                        

                    }

                //START CATCH FOR IF-SEIRES-COMMAND    
                }catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                        System.out.println(RED + "Couldnt find " + RESET + YELLOW + seriesParts[0] + RESET);
                        System.out.println(BLACK+"Try Again ("+YELLOW+"y"+BLACK+"/"+RED+"n"+BLACK+")\nSearch for another series ("+BLUE+"c"+BLACK+")"+RESET);
                        String yorno = in.nextLine();
                        if (yorno.equals("y") || yorno.equals("Y")) {
                            ncv2.space("");
                        } else if (yorno.equals("n") || yorno.equals("N")) {
                            ncv2.space(RED + "Exiting..." + RESET);
                            return;
                        }else if (yorno.equals("c") || yorno.equals("C")) {
                            break outerLoop;
                        }else {
                            while (true) {
                                ncv2.space("");
                                System.out.println(RED + "Invalid input!" + RESET);
                                yorno = in.nextLine();
                                if (yorno.equals("y") || yorno.equals("Y")) {
                                    ncv2.space("");
                                    break;
                                } else if (yorno.equals("n") || yorno.equals("N")) {
                                    ncv2.space(RED + "Exiting..." + RESET);
                                    return;
                                }
                            }
                        }
                    
                }
                //END IF-SERIES-COMMAND




                }
                //END OUTERLOOP FOR IF-SERIES-COMMAND


            } 
            //END IF-SERIES-COMMAND



            else{
                ncv2.space(RED + " ".repeat(18) + "Unknown command" + RESET);
            }
            //end second if    
            }
        
        }catch (Exception e) {
            ncv2.space(RED + " ".repeat(33) + "Error" + RESET);
            
        }
        //END TRY 1
    }
   

    //Running
    public static void main(String[] args) {
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        try {
            System.setProperty("webdriver.gecko.driver", "lib\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("--headless"); 
            WebDriver driver = new FirefoxDriver(options);
            App(driver);
        } catch (Exception e) {
            System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); 
            WebDriver driver = new ChromeDriver(options);
            App(driver);
        }
    }
    
}
//END RUN



