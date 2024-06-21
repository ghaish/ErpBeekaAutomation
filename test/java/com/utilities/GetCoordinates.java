package com.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.BaseTest;

public class GetCoordinates extends BaseTest {
	
	public static void getCoordinatesWithinElement(WebElement element, double xPercentage, double yPercentage) {
//        Dimension size = element.getSize();
//        int xOffset = (int) (size.getWidth() * xPercentage);
//        int yOffset = (int) (size.getHeight() * yPercentage);
//        
//     // Adjust offsets to ensure they fall within the element boundaries
//        xOffset = Math.min(xOffset, size.getWidth() - 1);
//        yOffset = Math.min(yOffset, size.getHeight() - 1);
//
//        return new Point(xOffset, yOffset);
        
        
        
        // Get the dimensions of the target element
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();

        // Calculate the center coordinates of the target element
        int xOffset = elementWidth / 2;
        int yOffset = elementHeight / 2;
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element, xOffset, yOffset).click().build().perform();
//        return new Point(xOffset, yOffset);
    }
}
