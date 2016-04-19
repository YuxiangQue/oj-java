package com.placeholder.test.mstest2015sept2;

/**
 * http://blog.csdn.net/zone_programming/article/details/49129749
 *
 * @author yuxiangque
 * @version 2016/4/12
 */
public class FarthestPoint {

    public static void main(String[] args) {
        int[] maxX = new int[1];
        int[] maxY = new int[1];
        farthestPoint(1.0, 1.0, 5.0, maxX, maxY);
        System.out.printf("%d %d\n", maxX[0], maxY[0]);

        maxX = new int[1];
        maxY = new int[1];
        farthestPoint(0.9, 0.9, 5.0, maxX, maxY);
        System.out.printf("%d %d\n", maxX[0], maxY[0]);
    }

    public static void farthestPoint(double cx, double cy, double radius, int[] maxX, int[] maxY) {
        double radius2 = radius * radius;
        int left = (int) Math.ceil(cx - radius);
        int right = (int) Math.floor(cx + radius);
        double maxDistance2 = 0.0;
        for (int x1 = right; x1 >= left; --x1) {
            double temp = Math.sqrt(radius2 - (x1 - cx) * (x1 - cx));
            int y1 = (int) Math.floor(cy + temp);
            double distance2 = (x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy);
            if (distance2 <= radius2 && distance2 > maxDistance2) {
                maxDistance2 = distance2;
                maxX[0] = x1;
                maxY[0] = y1;
            }
            y1 = (int) Math.ceil(cy - temp);
            distance2 = (x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy);
            if (distance2 <= radius2 && distance2 > maxDistance2) {
                maxDistance2 = distance2;
                maxX[0] = x1;
                maxY[0] = y1;
            }
        }
    }
}
