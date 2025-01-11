//START IMPORT-SECTION
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Scanner;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.ArrayList;
import java.io.PrintStream;


class ncv2{
    
    

    public static String cleanFileName(String seriesName) {
        
        String invalidCharacters = "[\\\\/:*?\"<>|]"; 
       
        return seriesName.replaceAll(invalidCharacters, "_");
    }

    static ArrayList<Integer> safeFind_epCount(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> safefind = new ArrayList<>();
       try {
        try { 
            WebElement epCoun = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[2]/div[1]/a/h3/span[2]")));
            String textEpCoun = epCoun.getText();
            safefind.add(Integer.parseInt(textEpCoun));
            safefind.add(0);
            return safefind;
        } catch (Exception e) {
            WebElement epCoun = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[1]/div/div[1]/a/span[2]")));
            String textEpCoun = epCoun.getText();
            safefind.add(Integer.parseInt(textEpCoun));
            safefind.add(1);
            return safefind;
        }
       } catch (Exception e) {
        System.out.println(run.LIGHT_RED+"Couldnt find the Episodes count, Please insert it manually"+run.RESET);
        System.out.println(": ");
        safefind.add(in.nextInt());safefind.add(1);
        return safefind;
       }
       
      
    }
    static void ncO(String series, WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        long startTime1 = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebDriverWait waitForStoryline = new WebDriverWait(driver, Duration.ofMillis(2000));

        //click episodes
        ArrayList<Integer> numberEpcounArr = safeFind_epCount(driver);
        int numberEpcoun = numberEpcounArr.get(0);
        if (numberEpcounArr.get(1) == 1) {
            try {
                WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[1]/div[1]/a")));
                String hrefEpisodes = episodes.getAttribute("href");
                run.outputImdb("Extracting episodes Url (" + run.BLUE + hrefEpisodes + run.RESET + ")");
                driver.get(hrefEpisodes);
            } catch (Exception e) {
                WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[2]/div[1]/a")));
                String hrefEpisodes = episodes.getAttribute("href");
                run.outputImdb("Extracting episodes Url (" + run.BLUE + hrefEpisodes + run.RESET + ")");
                driver.get(hrefEpisodes);
            }
            
           
        }else{
            WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[2]/div[1]/a")));
            String hrefEpisodes = episodes.getAttribute("href");
            run.outputImdb("Extracting episodes Url (" + run.BLUE + hrefEpisodes + run.RESET + ")");
            driver.get(hrefEpisodes);
        }
        
        
        //Actions actions = new Actions(driver);

        //click  first episode
        try {
        WebElement firstEpisode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]/section[2]/article[1]/div/div/div[3]/div[1]/h4/div/a")));
        String hrefFirstEpisode = firstEpisode.getAttribute("href");
        run.outputImdb("Extracting first episode Url (" + run.BLUE + hrefFirstEpisode + run.RESET + ")");
        driver.get(hrefFirstEpisode);
        } catch (Exception e) {
            WebElement firstEpisode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]/section[2]/article[1]/div/div/div[3]/div[2]/h4/div/a")));
            String hrefFirstEpisode = firstEpisode.getAttribute("href");
            run.outputImdb("Extracting first episode Url (" + run.BLUE + hrefFirstEpisode + run.RESET + ")");
            driver.get(hrefFirstEpisode);
        }
        
        
        
        long endTime1 = System.currentTimeMillis();
        long timeTaken1 = (endTime1 - startTime1) / 1000;
        run.outputDone(timeTaken1);
        run.outputNc("Strating loop");



        for (int i = 1; i < numberEpcoun; i++) {
            try {
            long startTime2 = System.currentTimeMillis();
            run.outputNc("Loop - " + run.BLUE + i + run.RESET);
                
                // storyline

                WebElement seasonEpisodeElement = driver.findElement(By.cssSelector("div[data-testid='hero-subnav-bar-season-episode-numbers-section']"));
                String seasonEpisodeText = seasonEpisodeElement.getText().trim();
                String seasonFolder = seasonEpisodeText.substring(0, seasonEpisodeText.indexOf('.'));
                String episodeString = seasonEpisodeText.substring(seasonEpisodeText.indexOf('.') + 1);
                WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.sc-d541859f-1.imUuxf")));
                String rating = ratingElement.getText().trim(); 
                run.outputImdb("Getting Episode " + run.BLUE + seasonEpisodeText + " Rating: " + run.CYAN + rating + run.RESET );
                WebElement seriesLinkElement = driver.findElement(By.xpath("//a[@data-testid='hero-title-block__series-link']"));
                String seriesTitle = seriesLinkElement.getText().trim();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                int count = 0;
                int Scrolingcount = 1;
                while (true) {
                    try {
                        WebElement storyline = waitForStoryline.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ipc-metadata-list-item__icon-link[aria-label='Parents guide: see all']")));
                        run.outputImdb("Found the Storyline");
                        String hrefStoryline = storyline.getAttribute("href");
                        run.outputImdb("Extracting parents guide Url (" + run.BLUE + hrefStoryline + run.RESET + ")"); 
                        driver.navigate().to(hrefStoryline); 
                        break;
                    } catch (Exception e) {
                        
                        js.executeScript("window.scrollBy(0,800);");
                        run.outputImdb("Scrolling number " + run.YELLOW + Scrolingcount + run.RESET);
                        count++;
                        Scrolingcount++;
                        if (count > 5) {
                            try {
                                ncv2.anotherParentsGuideFounderO(driver);
                                break;
                            } catch (Exception innerException) {
                                run.outputImdb(run.RED+"Couldnt find any data");
                                WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
                                run.outputImdb(run.RESET+"Reaching for the next Episode");
                                driver.get(next.getAttribute("href"));
                                run.outputImdb("Extracting next episode  Url (" + run.BLUE + next.getAttribute("href")+ run.RESET + ")"); 
                                continue; 
                            }
                            
                        }
                    }  
                }
            
                // Screenshot 
                
                WebElement epName = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section/section/div[3]/section/section/div[2]/hgroup/h2"));
                WebElement nudity = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]"));
                WebElement violence = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[3]"));
                WebElement profanity = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[4]"));
                WebElement drugs = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[5]"));
                WebElement frightening = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[6]"));
                
                File nudity_screenshot = ((TakesScreenshot) nudity).getScreenshotAs(OutputType.FILE);
                File violence_screenshot = ((TakesScreenshot) violence).getScreenshotAs(OutputType.FILE);
                File profanity_screenshot = ((TakesScreenshot) profanity).getScreenshotAs(OutputType.FILE);
                File drugs_screenshot = ((TakesScreenshot) drugs).getScreenshotAs(OutputType.FILE);
                File frightening_screenshot = ((TakesScreenshot) frightening).getScreenshotAs(OutputType.FILE);

                String epNameText = epName.getText().trim();
                run.outputImdb("Getting Episode " + run.BLUE + seasonEpisodeText + " Title: " + run.CYAN + epNameText + run.RESET );
                try {
                    File n_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Nudity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File v_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Violence and Gore/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File p_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Profanity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File d_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Alcohol and Drugs/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File f_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Frightening and Intense/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    
                    FileUtils.copyFile(nudity_screenshot, n_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.RED +"Nudity"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(violence_screenshot, v_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.BLUE +"Violence and Gore"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(profanity_screenshot, p_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.MAGENTA +"Profanity"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(drugs_screenshot, d_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.YELLOW +"Alcohol and Drugs"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(frightening_screenshot, f_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.LIGHT_RED +"Frightening and Intense"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    try {
                        WebElement certificates = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[7]"));
                        File certificates_screenshot = ((TakesScreenshot) certificates).getScreenshotAs(OutputType.FILE);
                        File c_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Certifications/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                        FileUtils.copyFile(certificates_screenshot, c_destination);
                        run.outputSavingpic(run.GREEN + "Saved " + run.BLUE +"/"+run.CYAN +"Certifications"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);;
                    } catch (Exception e) {
                        
                    }
                } catch (Exception e) {
                    File n_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Nudity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File v_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Violence and Gore/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File p_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Profanity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File d_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Alcohol and Drugs/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File f_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Frightening and Intense/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File c_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Certifications/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    FileUtils.copyFile(nudity_screenshot, n_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.RED +"Nudity"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(violence_screenshot, v_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.BLUE +"Violence and Gore"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(profanity_screenshot, p_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.MAGENTA +"Profanity"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(drugs_screenshot, d_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.YELLOW +"Alcohol and Drugs"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(profanity_screenshot, f_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.LIGHT_RED +"Frightening and Intense"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);
                    FileUtils.copyFile(drugs_screenshot, c_destination);
                    run.outputSavingpic(run.GREEN + "Saved " + run.RESET +"/"+run.CYAN +"Certifications"+run.RESET+"/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png"+run.RESET);;
                }
        
                // Back to episode
                
                WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/section/div[3]/section/section/div[1]/a")));
                String hrefBack = back.getAttribute("href");
                run.outputImdb("Extracting Back Url (" + run.BLUE + hrefBack + run.RESET + ")"); 
                driver.navigate().to(hrefBack);  
        
               
                try {
                    WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
                    run.outputImdb("Extracting next episode Url (" + run.BLUE + next.getAttribute("href").trim() + run.RESET + ")");
                    next.click();  

                } catch (Exception e) {
                    
                }
                long endTime2 = System.currentTimeMillis();
                long timeTaken2 = (endTime2 - startTime2) / 1000;
                run.outputDone(timeTaken2);
                run.outputNc(run.MAGENTA + seasonEpisodeText + run.RESET + " Done    -    " +run.GREEN+ i+run.RESET+"/" + run.BLUE+ numberEpcoun + run.RESET + "  Elapsed: " + run.YELLOW +String.format("%.2f", (double)timeTaken2) + run.RESET + " Seconds");
            } catch (Exception e) {
                run.outputNc("Skiping loop: " + run.BLUE + i + run.RESET);
            }
        }
        long endTime3 = System.currentTimeMillis();
        long timeTaken3 = (endTime3 - startTime1) / 1000;
        run.outputNc("Overall elapsed time: " + run.YELLOW + (double) timeTaken3/60 + run.RESET +" Minutes");
    
        
    }
    
    static void nc(String series, WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        long startTime1 = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebDriverWait waitForStoryline = new WebDriverWait(driver, Duration.ofMillis(2000));
        //search

        

        //click episodes
        ArrayList<Integer> numberEpcounArr = safeFind_epCount(driver);
        int numberEpcoun = numberEpcounArr.get(0);
        if (numberEpcounArr.get(1) == 1) {
            try {
                WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[1]/div[1]/a")));
                String hrefEpisodes = episodes.getAttribute("href");
                driver.get(hrefEpisodes);
            } catch (Exception e) {
                WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[2]/div[1]/a")));
                String hrefEpisodes = episodes.getAttribute("href");
                driver.get(hrefEpisodes);
            }
           
        }else{
            WebElement episodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/div/section/div/div[1]/section[2]/div[1]/a")));
            String hrefEpisodes = episodes.getAttribute("href");
            
            driver.get(hrefEpisodes);
        }
        
        
        //Actions actions = new Actions(driver);

        //click  first episode
        try {
        WebElement firstEpisode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]/section[2]/article[1]/div/div/div[3]/div[1]/h4/div/a")));
        String hrefFirstEpisode = firstEpisode.getAttribute("href");
        
        driver.get(hrefFirstEpisode);
        } catch (Exception e) {
            WebElement firstEpisode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]/section[2]/article[1]/div/div/div[3]/div[2]/h4/div/a")));
            String hrefFirstEpisode = firstEpisode.getAttribute("href");
            
            driver.get(hrefFirstEpisode);
        }
        
        
        
        long endTime1 = System.currentTimeMillis();
        long timeTaken1 = (endTime1 - startTime1) / 1000;
        
        
    



        for (int i = 1; i <= numberEpcoun; i++) {
            try {
            long startTime2 = System.currentTimeMillis();
            
                
                // storyline

                WebElement seasonEpisodeElement = driver.findElement(By.cssSelector("div[data-testid='hero-subnav-bar-season-episode-numbers-section']"));
                String seasonEpisodeText = seasonEpisodeElement.getText().trim();
                String seasonFolder = seasonEpisodeText.substring(0, seasonEpisodeText.indexOf('.'));
                String episodeString = seasonEpisodeText.substring(seasonEpisodeText.indexOf('.') + 1);
                WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.sc-d541859f-1.imUuxf")));
                String rating = ratingElement.getText().trim(); 
                
                WebElement seriesLinkElement = driver.findElement(By.xpath("//a[@data-testid='hero-title-block__series-link']"));
                String seriesTitle = seriesLinkElement.getText().trim();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                int count = 0;
                int Scrolingcount = 1;
                while (true) {
                    try {
                        WebElement storyline = waitForStoryline.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ipc-metadata-list-item__icon-link[aria-label='Parents guide: see all']")));
                       
                        String hrefStoryline = storyline.getAttribute("href");
                        
                        driver.navigate().to(hrefStoryline); 
                        break;
                    } catch (Exception e) {
                        
                        js.executeScript("window.scrollBy(0,800);");
                        
                        count++;
                        Scrolingcount++;
                        if (count > 5) {
                            try {
                                ncv2.anotherParentsGuideFounder(driver);
                                break;
                            } catch (Exception innerException) {
                        
                                WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
                        
                                driver.get(next.getAttribute("href"));
                                continue;
                            
                            }
                            
                        }
                    }  
                }
            
                // Screenshot 
                
                WebElement epName = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section/section/div[3]/section/section/div[2]/hgroup/h2"));
                WebElement nudity = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[2]"));
                WebElement violence = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[3]"));
                WebElement profanity = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[4]"));
                WebElement drugs = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[5]"));
                WebElement frightening = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[6]"));
                
                File nudity_screenshot = ((TakesScreenshot) nudity).getScreenshotAs(OutputType.FILE);
                File violence_screenshot = ((TakesScreenshot) violence).getScreenshotAs(OutputType.FILE);
                File profanity_screenshot = ((TakesScreenshot) profanity).getScreenshotAs(OutputType.FILE);
                File drugs_screenshot = ((TakesScreenshot) drugs).getScreenshotAs(OutputType.FILE);
                File frightening_screenshot = ((TakesScreenshot) frightening).getScreenshotAs(OutputType.FILE);

                String epNameText = epName.getText().trim();
                
                try {
                    File n_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Nudity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File v_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Violence and Gore/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File p_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Profanity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File d_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Alcohol and Drugs/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File f_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Frightening and Intense/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                   
                    FileUtils.copyFile(nudity_screenshot, n_destination);
                    
                    FileUtils.copyFile(violence_screenshot, v_destination);
                    
                    FileUtils.copyFile(profanity_screenshot, p_destination);
                    
                    FileUtils.copyFile(drugs_screenshot, d_destination);

                    FileUtils.copyFile(frightening_screenshot, f_destination);
                    try {
                        WebElement certificates = driver.findElement(By.xpath("/html/body/div[2]/main/div/section/div/section/div/div[1]/section[7]"));
                        File certificates_screenshot = ((TakesScreenshot) certificates).getScreenshotAs(OutputType.FILE);
                        File c_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Certifications/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                        FileUtils.copyFile(certificates_screenshot, c_destination);
                    } catch (Exception e) {
                        
                    }
                } catch (Exception e) {
                    File n_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Nudity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File v_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Violence and Gore/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File p_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Profanity/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File d_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Alcohol and Drugs/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File f_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Frightening and Intense/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    File c_destination = new File("Pictures/"+ncv2.cleanFileName(seriesTitle)+ "/Certifications/" +seasonFolder+"/"+ episodeString + " " + "("+ncv2.cleanFileName(epNameText)+") - "+ rating + " .png");
                    FileUtils.copyFile(nudity_screenshot, n_destination);
                    FileUtils.copyFile(violence_screenshot, v_destination);
                    FileUtils.copyFile(profanity_screenshot, p_destination);
                    FileUtils.copyFile(drugs_screenshot, d_destination);
                    FileUtils.copyFile(frightening_screenshot, f_destination);
                    
                }
                
        
                // Back to episode
                
                WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section/section/div[3]/section/section/div[1]/a")));
                String hrefBack = back.getAttribute("href");
               
                driver.navigate().to(hrefBack);  
        
               
                try {
                    WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
               
                    next.click();  

                } catch (Exception e) {
                    
                }
                long endTime2 = System.currentTimeMillis();
                long timeTaken2 = (endTime2 - startTime2) / 1000;
                System.out.println(run.MAGENTA + seasonEpisodeText + run.RESET + " Done    -    " +run.GREEN+ i+run.RESET+"/" + run.BLUE+ numberEpcoun + run.RESET + "  Elapsed: " + run.YELLOW + (double)timeTaken2 + run.RESET + " Seconds");
            } catch (Exception e) {
                
            }
        }
        long endTime3 = System.currentTimeMillis();
        long timeTaken3 = (endTime3 - startTime1) / 1000;
        space(run.GREEN + " ".repeat(33) + "Done in " + run.YELLOW +String.format("%.2f", (double)timeTaken3/60)+ run.RESET+ "Minutes"  );    
        
    }    
     

    public static void space(String loadingStatus){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        run.clearConsole();
        System.out.println(loadingStatus+run.RESET);
    }

    public static ArrayList<String> header(String series, WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        space("Searching.");
        //declartions 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        space("Searching..");

        //open 
        driver.get("https://www.imdb.com/?ref_=nv_home");
        WebElement imdbSearch = driver.findElement(By.id("suggestion-search"));
        imdbSearch.sendKeys(series);imdbSearch.sendKeys(Keys.ENTER); 
        space("Searching...");
        //click series
        WebElement firstLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/a")));
        String hrefSeries = firstLink.getAttribute("href");
        driver.get(hrefSeries);
        space("Searching.");
        //scraping series
        WebElement seriesTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/h1/span")));
        String seriesTitle = seriesTitleElement.getText().trim();
        WebElement seriesDateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[2]/a")));
        String seriesDate = seriesDateElement.getText().trim();
        String[] seriesDateParts = seriesDate.split("–",5);
        space("Searching..");
        WebElement seriesRateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[2]/div/div[1]/a/span/div/div[2]/div[1]/span[1]")));
        String seriesRate = seriesRateElement.getText().trim();
        WebElement seriesBriefElement = driver.findElement(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[3]/div[2]/div[1]/section/p"));
        space("Searching...");
        String seriesBrief = seriesBriefElement.getText().trim();
        space("Searching.");
        space("");
        System.out.println(run.BLUE+"                                               -------------"+run.RESET);
        System.out.println(run.BLACK +seriesBrief + run.RESET);
        System.out.println(run.BLUE+"                                               -------------"+run.RESET);
        
        ArrayList<String> output = new ArrayList<>();
       
            if (seriesDateParts.length==1) {
                output.add(seriesTitle);output.add(seriesDateParts[0]);output.add("no");output.add(seriesRate);
                return output;
            }
            if (!(seriesDateParts[1].matches("^([1-2][0-9]{3})$"))) {
                seriesDateParts[1] = "NOW";
            }
            output.add(seriesTitle);output.add(seriesDateParts[0]);output.add(seriesDateParts[1]);output.add(seriesRate);
            return output;
        
        
        
    }
    
    public static ArrayList<String> headerO(String series, WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
        long startTime = System.currentTimeMillis();
        //declartions 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        run.outputImdb("Getting IMDB");

        //open 
        driver.get("https://www.imdb.com/?ref_=nv_home");
        WebElement imdbSearch = driver.findElement(By.id("suggestion-search"));
        imdbSearch.sendKeys(series);imdbSearch.sendKeys(Keys.ENTER); 
        run.outputImdb("Searching for " + run.YELLOW + series + run.RESET);
        //click series
        WebElement firstLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/a")));
        String hrefSeries = firstLink.getAttribute("href");
        run.outputImdb("Extracting Url (" + run.BLUE + hrefSeries+run.RESET+")");
        driver.get(hrefSeries);
        
        //scraping series
        WebElement seriesTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/h1/span")));
        String seriesTitle = seriesTitleElement.getText().trim();
        run.outputImdb("Extracting " + run.CYAN + "Title" + run.RESET);
        WebElement seriesDateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[2]/a")));
        String seriesDate = seriesDateElement.getText().trim();
        run.outputImdb("Extracting " + run.CYAN + "Date" + run.RESET);
        String[] seriesDateParts = seriesDate.split("–",5);
        
        WebElement seriesRateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[2]/div/div[1]/a/span/div/div[2]/div[1]/span[1]")));
        String seriesRate = seriesRateElement.getText().trim();
        run.outputImdb("Extracting " + run.CYAN + "Rate" + run.RESET);
        WebElement seriesBriefElement = driver.findElement(By.xpath("/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[3]/div[2]/div[1]/section/p"));
        String seriesBrief = seriesBriefElement.getText().trim();
        run.outputImdb("Extracting " + run.CYAN + "Plot" + run.RESET);
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime) / 1000;
        System.out.println("[DONE] " + run.GREEN + timeTaken + " Seconds" + run.RESET);

        
        System.out.println(run.BLUE+"                                               -------------"+run.RESET);
        System.out.println(run.BLACK +seriesBrief + run.RESET);
        System.out.println(run.BLUE+"                                               -------------"+run.RESET);
        ArrayList<String> output = new ArrayList<>();
        
        if (seriesDateParts.length == 1 || seriesDateParts.length < 1) {
            output.add(seriesTitle);output.add(seriesDateParts[0]);output.add("no");output.add(seriesRate);
            return output;
        }

        if (!(seriesDateParts[1].matches("^([1-2][0-9]{3})$"))) {
            seriesDateParts[1] = "NOW";
        }
        output.add(seriesTitle);output.add(seriesDateParts[0]);output.add(seriesDateParts[1]);output.add(seriesRate);
        return output;
        
    }
    static String testInternet(WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
       //declartions 
       ncv2.space(run.YELLOW+"Connecting..."+run.RESET);
       while (true) {
        try {
            driver.get("https://www.google.com/");
            break;
           } catch (Exception e) {
            Scanner in = new Scanner(System.in);
            ncv2.space(run.RED+"Failed to connect"+run.RESET);
            System.out.println("Try again (y/n)");
            String choice = in.nextLine();
            if (choice.equals("y")||choice.equals("Y")) {
                
            }else if (choice.equals("n")||choice.equals("N")) {
                return "end";
            }else{

            }
           }
       }
       
       
       return "a";
    }
    static String testInternetO( WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
       //declartions 
       Scanner in = new Scanner(System.in);
       run.outputNc("Connecting");
       while (true) {
        try {
            driver.get("https://www.google.com/");
            run.outputNc("Connected");
            break;
           } catch (Exception e) {
            run.outputNc("Failed to connect");
            run.outputNc("Try again? y/n");
            String choice = in.nextLine();
            if (choice.equals("y")||choice.equals("Y")) {
                
            }else if (choice.equals("n")||choice.equals("N")) {
                return "end";
            }else{

            }
           }
       }
       
       
       return "a";
    }
    static void anotherParentsGuideFounderO(WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(800));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                run.outputNc("Trying another method");
                int count = 0;
                int Scrolingcount = 1;
                while (true) {
                    try {
                        WebElement storyline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='storyline-parents-guide']//a[contains(@class, 'ipc-metadata-list-item__list-content-item')]")));
                        run.outputImdb("Found the Storyline");
                        String hrefStoryline = storyline.getAttribute("href");
                        run.outputImdb("Extracting parents guide Url (" + run.BLUE + hrefStoryline + run.RESET + ")"); 
                        driver.navigate().to(hrefStoryline); 
                        return;
                    } catch (Exception e) {
                        
                        js.executeScript("window.scrollBy(0,800);");
                        run.outputImdb("Scrolling number " + run.YELLOW + Scrolingcount + run.RESET);
                        count++;
                        Scrolingcount++;
                        if (count > 5) {
                            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
                            run.outputImdb(run.RED+"No Parents guide for this episode, " + run.RESET+"Reaching for the next Episode");
                            driver.get(next.getAttribute("href"));
                            run.outputImdb("Extracting next episode  Url (" + run.BLUE + next.getAttribute("href")+ run.RESET + ")"); 
                            
                        }
                    }  
                }
      
    }
    static void anotherParentsGuideFounder(WebDriver driver){
        System.setErr(new PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(800));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                
                int count = 0;
                int Scrolingcount = 1;
                while (true) {
                    try {
                        WebElement storyline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='storyline-parents-guide']//a[contains(@class, 'ipc-metadata-list-item__list-content-item')]")));
                        String hrefStoryline = storyline.getAttribute("href");
                        driver.navigate().to(hrefStoryline); 
                        return;
                    } catch (Exception e) {
                        
                        js.executeScript("window.scrollBy(0,800);");
                        count++;
                        Scrolingcount++;
                        if (count > 5) {
                            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='hero-subnav-bar-next-episode-button']")));
                            driver.get(next.getAttribute("href"));
                            
                        }
                    }  
                }
      
}
}        
