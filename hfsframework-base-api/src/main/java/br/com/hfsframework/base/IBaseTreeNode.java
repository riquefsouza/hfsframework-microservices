/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBaseTreeNode.
 */
public interface IBaseTreeNode {

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType();
    
    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type);

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData();
	
	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<IBaseTreeNode> getChildren();
	
	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public IBaseTreeNode getParent();
	
	/**
	 * Sets the parent.
	 *
	 * @param treeNode the new parent
	 */
	public void setParent(IBaseTreeNode treeNode);

	/**
	 * Checks if is expanded.
	 *
	 * @return true, if is expanded
	 */
	public boolean isExpanded();
	
	/**
	 * Sets the expanded.
	 *
	 * @param expanded the new expanded
	 */
	public void setExpanded(boolean expanded);

	/**
	 * Gets the child count.
	 *
	 * @return the child count
	 */
	public int getChildCount();
	
	/**
	 * Checks if is leaf.
	 *
	 * @return true, if is leaf
	 */
	public boolean isLeaf();

    /**
     * Checks if is selected.
     *
     * @return true, if is selected
     */
    public boolean isSelected();

    /**
     * Sets the selected.
     *
     * @param value the new selected
     */
    public void setSelected(boolean value);
    
    /**
     * Checks if is selectable.
     *
     * @return true, if is selectable
     */
    public boolean isSelectable();
	
	/**
	 * Sets the selectable.
	 *
	 * @param selectable the new selectable
	 */
	public void setSelectable(boolean selectable);
    
    /**
     * Checks if is partial selected.
     *
     * @return true, if is partial selected
     */
    public boolean isPartialSelected();
    
    /**
     * Sets the partial selected.
     *
     * @param value the new partial selected
     */
    public void setPartialSelected(boolean value);
    
    /**
     * Sets the row key.
     *
     * @param rowKey the new row key
     */
    public void setRowKey(String rowKey);
    
    /**
     * Gets the row key.
     *
     * @return the row key
     */
    public String getRowKey();
    
    /**
     * Clear parent.
     */
    public void clearParent();
}
