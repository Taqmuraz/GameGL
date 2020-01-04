package toolbox;

public interface Predicate<TParam, TResoult>
{
	TResoult getResoult (TParam param);
}