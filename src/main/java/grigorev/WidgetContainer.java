package grigorev;

import grigorev.data.Widget;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class WidgetContainer {

    @Getter
    private List<Widget> widgets = new ArrayList<Widget>();

    public void addWidget(Widget widget) {
        this.widgets.add(widget);
    }

    public void displayWidgets() {
        for (Widget widget : widgets) {
            displayWidget(widget);
        }
    }

    private void displayWidget(Widget widget) {

        System.out.println("Display widget with name " + widget.getName());

        if (widget.getWidgets() != null) {
            for (Widget childWidget : widget.getWidgets()) {
                displayWidget(childWidget);
            }
        }
    }
}
