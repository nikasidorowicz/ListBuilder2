package objects;

public interface HierarchicalController<P extends HierarchicalController<?>> {
    public P getParentController();
    void setParentController(WarbandView parent);
}