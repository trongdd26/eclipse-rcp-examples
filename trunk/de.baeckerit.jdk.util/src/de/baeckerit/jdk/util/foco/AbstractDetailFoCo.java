package de.baeckerit.jdk.util.foco;


public abstract class AbstractDetailFoCo implements IFoCo {
	private final IFoCo getter;
	private final boolean nullGreater;

	public abstract Object getDetail(Object object);

	public AbstractDetailFoCo(IFoCo getter, boolean nullGreater) {
		this.getter = getter;
		this.nullGreater = nullGreater;
	}

	public AbstractDetailFoCo(IFoCo getter) {
		this(getter, true);
	}

	@Override
	public String format(Object object) {
		Object detail = getDetail(object);
		return detail == null ? "" : getter.format(detail);
	}

	@Override
	public int compare(Object left, Object right) {
		Object lhs = getDetail(left);
		Object rhs = getDetail(right);
		if (lhs == null) {
			return rhs == null ? 0 : nullGreater ? 1 : -1;
		} else {
			return rhs != null ? getter.compare(lhs, rhs) : nullGreater ? -1 : 1;
		}
	}
}
