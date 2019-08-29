public static void sortShapes(Shape[] array,
                              List<Shape> shapes,
                              List<Polygon> polygons,
                              List<Square> squares,
                              List<Circle> circles) {
   for(Shape sh : array){
       if(Circle.class.isInstance(sh)){
           circles.add((Circle)sh);
        }else if(Square.class.isInstance(sh)){
           squares.add((Square)sh);
        }else if(Polygon.class.isInstance(sh)){
           polygons.add((Polygon)sh);
        }else if(Shape.class.isInstance(sh)){
           shapes.add(sh);
        }
   }
}