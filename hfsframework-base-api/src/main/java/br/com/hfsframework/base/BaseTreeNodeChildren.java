/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTreeNodeChildren.
 */
public class BaseTreeNodeChildren extends BaseTreeNodeList {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The parent. */
	private IBaseTreeNode parent;
    
    /**
     * Instantiates a new base tree node children.
     *
     * @param parent the parent
     */
    public BaseTreeNodeChildren(IBaseTreeNode parent) {
        this.parent = parent;
    }
    
    /**
     * Erase parent.
     *
     * @param node the node
     */
    private void eraseParent(IBaseTreeNode node) {
        IBaseTreeNode parentNode = node.getParent();
        if(parentNode != null) {
            parentNode.getChildren().remove(node);
            node.setParent(null);
        }
    }
    
    /* (non-Javadoc)
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    @Override
    public boolean add(IBaseTreeNode node) {
        if(node == null) {
            throw new NullPointerException();
        }
        else {
            eraseParent(node);
            boolean result = super.add(node);
            node.setParent(parent);
            updateRowKeys(parent);
            return result;
        }
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#add(int, java.lang.Object)
     */
    @Override
    public void add(int index, IBaseTreeNode node) {
        if(node == null) {
            throw new NullPointerException();
        }
        else if((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException();
        } 
        else {
            eraseParent(node);
            super.add(index, node);
            node.setParent(parent);
            updateRowKeys(parent);
        }
    }
    
    /* (non-Javadoc)
     * @see java.util.ArrayList#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends IBaseTreeNode> collection) {
        Iterator<IBaseTreeNode> elements = (new ArrayList<IBaseTreeNode>(collection)).iterator();
        boolean changed = false;
        while(elements.hasNext()) {
            IBaseTreeNode node = elements.next();
            if(node == null) {
                throw new NullPointerException();
            } 
            else {
                eraseParent(node);
                super.add(node);
                node.setParent(parent);
                changed = true;
            }
        }
        
        if(changed) {
            updateRowKeys(parent);
        }
        
        return (changed);
    }
    
    /* (non-Javadoc)
     * @see java.util.ArrayList#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(int index, Collection<? extends IBaseTreeNode> collection) {
        Iterator<IBaseTreeNode> elements = (new ArrayList<IBaseTreeNode>(collection)).iterator();
        boolean changed = false;
        while(elements.hasNext()) {
            IBaseTreeNode node = elements.next();
            if(node == null) {
                throw new NullPointerException();
            } 
            else {
                eraseParent(node);
                super.add(index++, node);
                node.setParent(parent);
                changed = true;
            }
        }
        
        if(changed) {
            updateRowKeys(parent);
        }
        
        return (changed);
    }
    
    /* (non-Javadoc)
     * @see java.util.ArrayList#set(int, java.lang.Object)
     */
    @Override
    public IBaseTreeNode set(int index, IBaseTreeNode node) {
        if(node == null) {
            throw new NullPointerException();
        }
        else if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException();
        } 
        else {
            if(!parent.equals(node.getParent())) {
                eraseParent(node);
            }
            
            IBaseTreeNode previous = get(index);
            super.set(index, node);
            previous.setParent(null);
            node.setParent(parent);
            updateRowKeys(parent);
            return previous;
        }
    }
    
    /**
     * Optimized set implementation to be used in sorting.
     *
     * @param index index of the element to replace
     * @param node node to be stored at the specified position
     * @return the node previously at the specified position
     */
    public IBaseTreeNode setSibling(int index, IBaseTreeNode node) {
        if(node == null) {
            throw new NullPointerException();
        }
        else if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException();
        } 
        else {
            if(!parent.equals(node.getParent())) {
                eraseParent(node);
            }
            
            IBaseTreeNode previous = get(index);
            super.set(index, node);
            node.setParent(parent);
            updateRowKeys(parent);
            return previous;
        }
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#remove(int)
     */
    @Override
    public IBaseTreeNode remove(int index) {
        IBaseTreeNode node = get(index);
        node.setParent(null);
        super.remove(index);
        updateRowKeys(parent);
        return node;
    }

    /* (non-Javadoc)
     * @see java.util.ArrayList#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object object) {
        IBaseTreeNode node = (IBaseTreeNode) object;
        if(node == null) {
            throw new NullPointerException();
        }
        
        if(super.indexOf(node) != -1) {
            node.clearParent();
        }
        
        if(super.remove(node)) {
            updateRowKeys(parent);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Update row keys.
     *
     * @param node the node
     */
    private void updateRowKeys(IBaseTreeNode node) {
        int childCount = node.getChildCount();
        if(childCount > 0) {
            for(int i = 0; i < childCount; i++) {
                IBaseTreeNode childNode = node.getChildren().get(i);

                String childRowKey = (node.getParent() == null) ? String.valueOf(i) : node.getRowKey() + "_" + i;
                childNode.setRowKey(childRowKey);
                updateRowKeys(childNode);
            }
        }
    }
}
