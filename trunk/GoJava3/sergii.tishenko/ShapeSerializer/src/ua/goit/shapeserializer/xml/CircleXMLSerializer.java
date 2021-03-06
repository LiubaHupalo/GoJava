/**
 * Created by Aleksey Kurkov on 22.03.15.
 */

package ua.goit.shapeserializer.xml;

import ua.goit.shapeserializer.SerializeClassHolder;
import ua.goit.shapeserializer.SerializeXMLClassHolder;
import ua.goit.shapeserializer.SerializerFactory;
import ua.goit.shapeserializer.basicobjects.Circle;
import ua.goit.shapeserializer.basicobjects.Shape;

public class CircleXMLSerializer extends ShapeXMLSerializer {
  @Override
  public String serialize(Shape shape) {
    StringBuilder result = new StringBuilder();
    Circle circle = (Circle) shape;
    SerializeClassHolder classHolder = SerializerFactory.getSerializerFor("xml");
    
    result.append("<circle>\n<center>\n");
//  result.append(new SerializeXMLClassHolder().getSerializator(shape).serialize(circle.getCenter()));
    result.append(classHolder.serialize(circle.getCenter()));

    result.append("</center>\n<radius>");
    result.append(circle.getRadius());    
    result.append("</radius>");
    result.append("\n</circle>\n");
    return result.toString();
  }
}