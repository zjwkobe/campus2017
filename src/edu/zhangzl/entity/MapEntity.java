package edu.zhangzl.entity;

public class MapEntity implements Comparable<MapEntity> {
	private String str;
	private int sum;
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public int compareTo(MapEntity o) {
		// TODO Auto-generated method stub
		if(this.sum>o.getSum()) return 1;
		else{
			if(this.sum<o.getSum()) return -1;
			else{
				return this.str.compareTo(o.getStr());
			}
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapEntity other = (MapEntity) obj;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}
	
	
}
