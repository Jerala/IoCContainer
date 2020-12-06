package grigorev.eventhandlers;

import grigorev.data.Widget;

public interface WidgetMoveEventHandler {

    void handle(Widget widget, int oldX, int oldY);

}
