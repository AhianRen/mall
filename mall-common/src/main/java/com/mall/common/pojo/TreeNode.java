package com.mall.common.pojo;
/**
 * EasyUI的tree组件节点POJO
 * @author ren1
 *
 */
public class TreeNode {
	
	private Long id;
	private String text;
	//节点状态，open或closed
	private String state;
	
	public TreeNode(Long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
