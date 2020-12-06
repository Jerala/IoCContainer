package grigorev.eventhandlers;

import grigorev.data.Widget;

public interface WidgetResizeEventHandler {

    void handle(Widget widget, int oldWidth, int oldHeight);

}
