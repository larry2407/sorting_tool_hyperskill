public static int sumOfAreas(Shape[] array) {
    int currentArea = 0;
    int totalArea = 0;
    for(Shape s : array){
        if(Square.class.isInstance(s)){
            Square sq = (Square)s;
            currentArea = sq.getSide()*sq.getSide();
        }else if(Rectangle.class.isInstance(s)){
            Rectangle rec = (Rectangle)s;
            currentArea = rec.getWidth()*rec.getHeight();
        }
        totalArea+=currentArea;
        }
    return totalArea;
}