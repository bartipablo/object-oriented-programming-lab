package agh.ics.oop;

public class MapVisualizer {
    private static final String EMPTY_CELL = "&nbsp&nbsp";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";
    private final IWorldMap map;

    public MapVisualizer(IWorldMap map) {
        this.map = map;
    }

    public String draw(Vector2D lowerLeft, Vector2D upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y + 1; i >= lowerLeft.y - 1; i--) {
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                if (!(i < lowerLeft.y || i > upperRight.y)) {
                    builder.append(CELL_SEGMENT);
                    if (j <= upperRight.x) {
                        builder.append(drawObject(new Vector2D(j, i)));
                    }
                }
            }
            builder.append("<br/>");
        }
        return "<html> " +
                "<head>" +
                "<style>" +
                ".text{width: 1400px}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"text\">" +
                builder.toString() +
                "</div>" +
                "<body> " +
                "</html>";
    }

    private String drawObject(Vector2D currentPosition) {
        String result = null;
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                result = object.toString();
            } else {
                result = EMPTY_CELL;
            }
        } else {
            result = EMPTY_CELL;
        }
        return result;
    }
}