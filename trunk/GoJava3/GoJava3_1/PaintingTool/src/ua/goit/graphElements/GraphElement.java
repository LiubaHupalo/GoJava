package ua.goit.graphElements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class GraphElement implements Iterable <GraphElement> {
    protected String name;
    public GraphElement(String name) {
	this.name = name;
    }

    public abstract String getName();
    public abstract boolean isElement();
    public abstract String getType();
   
}