Ext.require([
 	'Ext.container.Viewport',
 	'Ext.form.*',
 	'Ext.layout.container.*',
    'Ext.tab.Panel',
 	'Ext.data.*',
 	'Ext.grid.*',
 	'Ext.panel.*',
 	'Ext.dd.*',
 	'Ext.Img',	
	'Ext.panel.*',
    'Ext.toolbar.*',
    'Ext.button.*',
    'Ext.container.ButtonGroup',
    'Ext.layout.container.Table',
    'Ext.dd.*',
    'Ext.Date.*',
    'Ext.chart.*',
    'Ext.layout.container.Fit',
    'Ext.window.MessageBox',
    'Ext.Window',
    'Ext.fx.target.Sprite',
    'Ext.ux.CheckColumn',
	'Ext.grid.column.Action',
	'Ext.selection.CheckboxModel',
	'Ext.tip.QuickTipManager'
 ]);

Ext.application({
	name 		: 'edu.uvm.survery',
	appFolder 	: '../../app/app',
	controllers : [],
	
	launch 		: function() {
		
		var link = document.createElement('link');
		link.type = 'image/x-icon';
		link.rel = 'shortcut icon';
		link.href = '../resources/images/icons/favicon.ico';
		document.getElementsByTagName('head')[0].appendChild(link); 
		
		Ext.create('Ext.container.Viewport', {
			name 	: 'viewportPrincipal',
			title 	: 'Principal',
			layout 	: 'fit', 
			items	: [{
				xtype: 'panel', 
				title: 'Test'
			}]
		});
		
	}
});