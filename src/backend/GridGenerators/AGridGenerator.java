package backend.GridGenerators;

public abstract class AGridGenerator implements IGridGenerator{

    @Override
    public abstract Grid generate(Object o);
}
