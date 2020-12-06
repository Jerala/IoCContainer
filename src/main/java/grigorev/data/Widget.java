package grigorev.data;

import grigorev.eventhandlers.WidgetMoveEventHandler;
import grigorev.eventhandlers.WidgetResizeEventHandler;
import grigorev.services.WidgetSerializer;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public abstract class Widget {

    private String name;
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int x;
    @Getter
    private int y;
    private List<Widget> widgets;
    private List<WidgetMoveEventHandler> moveEventHandlers = new ArrayList<WidgetMoveEventHandler>();
    private List<WidgetResizeEventHandler> resizeEventHandlers = new ArrayList<WidgetResizeEventHandler>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public void setSize(int width, int height) {
        int oldWidth = this.width;
        int oldHeight = this.height;
        this.width = width;
        this.height = height;
        for (WidgetResizeEventHandler handler: resizeEventHandlers) {
            handler.handle(this, oldWidth, oldHeight);
        }
    }

    public void setPosition(int x, int y) {
        int oldX = this.x;
        int oldY = this.y;
        this.x = x;
        this.y = y;
        for (WidgetMoveEventHandler handler: moveEventHandlers) {
            handler.handle(this, oldX, oldY);
        }
    }

    public void addMoveEventHandler(WidgetMoveEventHandler handler) {
        this.moveEventHandlers.add(handler);
    }

    public void addResizeEventHandler(WidgetResizeEventHandler handler) {
        this.resizeEventHandlers.add(handler);
    }

    public abstract WidgetType getWidgetType();

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator<WidgetSerializer> serializers = ServiceLoader.load(WidgetSerializer.class).iterator();

        boolean serializerFound = false;
        while(serializers.hasNext()) {
            WidgetSerializer serializer = serializers.next();
            if (serializer.getWidgetType().equals(this.getWidgetType())) {
                serializerFound = true;
                sb.append(serializer.serialize());
            }
        }
        if (!serializerFound) {
                throw new RuntimeException("There is no serializer for type " + getWidgetType());
        }
        if (widgets == null) {
            return sb.toString();
        }
        for (Widget childWidget: widgets) {
            sb.append(childWidget.serialize());
        }
        return sb.toString();
    }
}
