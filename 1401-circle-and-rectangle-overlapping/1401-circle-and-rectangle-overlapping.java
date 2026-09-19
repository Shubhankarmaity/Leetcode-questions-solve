class Solution {
    public boolean checkOverlap(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2) {

        // Find the closest point in the rectangle to the circle center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Distance from center to closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        // Overlap if distance <= radius
        return dx * dx + dy * dy <= radius * radius;
    }
}