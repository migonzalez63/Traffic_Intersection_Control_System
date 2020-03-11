package Graphics;

import Primary.DayNight;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Time of day display is used to overlay on GUI to denote time change
 */
public class TOD_Display{
    private GraphicsContext gc;
    private DayNight dn;
    private double x;
    private double y;

    public TOD_Display(GraphicsContext gc,double x, double y){
        this.gc = gc;
        this.x = x;
        this.y = y;
        dn = DayNight.DAY;
    }

    /**
     * Used to draw the rects
     */
    public void drawOverlay(){
        Paint color;
        boolean isDay = DayNight.DAY.getDay();

        if(isDay){
            color = Color.rgb(255,255,255, .1);
        } else{
//            color = Color.rgb(31, 42, 94,.1);
            color = Color.rgb(43, 47, 119,.1);
        }

        gc.setFill(color);
        gc.fillRect(x,y,225,225);
    }
}
