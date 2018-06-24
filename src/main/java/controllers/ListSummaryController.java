package controllers;

public class ListSummaryController implements HierarchicalController {

    private HierarchicalController parentController;

    @Override
    public HierarchicalController getParentController() {
        return parentController;
    }

    @Override
    public void setParentController(HierarchicalController parent) {
        parentController = parent;
    }
}
