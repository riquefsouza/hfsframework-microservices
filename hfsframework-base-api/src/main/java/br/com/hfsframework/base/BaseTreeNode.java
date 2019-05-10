/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTreeNode.
 */
public class BaseTreeNode implements IBaseTreeNode, Serializable {

	private static final long serialVersionUID = 1L;

	/** The Constant DEFAULT_TYPE. */
	public static final String DEFAULT_TYPE = "default";

	/** The type. */
	private String type;

	/** The data. */
	private Object data;

	/** The children. */
	private List<IBaseTreeNode> children;

	/** The parent. */
	private IBaseTreeNode parent;

	/** The expanded. */
	private boolean expanded;

	/** The selected. */
	private boolean selected;

	/** The selectable. */
	private boolean selectable = true;

	/** The row key. */
	private String rowKey;

	/**
	 * Instantiates a new base tree node.
	 */
	public BaseTreeNode() {
		this.type = DEFAULT_TYPE;
		this.children = new BaseTreeNodeChildren(this);
	}

	/**
	 * Instantiates a new base tree node.
	 *
	 * @param data the data
	 */
	public BaseTreeNode(Object data) {
		this.type = DEFAULT_TYPE;
		this.children = new BaseTreeNodeChildren(this);
		this.data = data;
	}

	/**
	 * Instantiates a new base tree node.
	 *
	 * @param data the data
	 * @param parent the parent
	 */
	public BaseTreeNode(Object data, IBaseTreeNode parent) {
		this.type = DEFAULT_TYPE;
		this.data = data;
		this.children = new BaseTreeNodeChildren(this);
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	/**
	 * Instantiates a new base tree node.
	 *
	 * @param type the type
	 * @param data the data
	 * @param parent the parent
	 */
	public BaseTreeNode(String type, Object data, IBaseTreeNode parent) {
		this.type = type;
		this.data = data;
		this.children = new BaseTreeNodeChildren(this);
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getType()
	 */
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setType(java.lang.String)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getData()
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getChildren()
	 */
	public List<IBaseTreeNode> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(List<IBaseTreeNode> children) {
		if (children instanceof BaseTreeNodeChildren) {
			this.children = children;
		} else {
			BaseTreeNodeChildren nodeChildren = new BaseTreeNodeChildren(this);
			nodeChildren.addAll(children);
			this.children = nodeChildren;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getParent()
	 */
	public IBaseTreeNode getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setParent(br.com.hfsframework.base.IBaseTreeNode)
	 */
	public void setParent(IBaseTreeNode parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#clearParent()
	 */
	public void clearParent() {
		this.parent = null;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#isExpanded()
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setExpanded(boolean)
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#isSelected()
	 */
	public boolean isSelected() {
		return this.selected;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setSelected(boolean)
	 */
	public void setSelected(boolean value) {
		this.selected = value;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#isSelectable()
	 */
	public boolean isSelectable() {
		return selectable;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setSelectable(boolean)
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getChildCount()
	 */
	public int getChildCount() {
		return children.size();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		if (children == null)
			return true;

		return children.isEmpty();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#getRowKey()
	 */
	public String getRowKey() {
		return rowKey;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setRowKey(java.lang.String)
	 */
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		BaseTreeNode other = (BaseTreeNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;

		if (rowKey == null) {
			if (other.rowKey != null)
				return false;
		} else if (!rowKey.equals(other.rowKey))
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (data != null)
			return data.toString();
		else
			return super.toString();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#isPartialSelected()
	 */
	public boolean isPartialSelected() {
		return false;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseTreeNode#setPartialSelected(boolean)
	 */
	public void setPartialSelected(boolean value) {
		// nothing
	}
}
