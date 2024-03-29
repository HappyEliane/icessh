package Common.controls.CheckBoxColum
{
	import mx.controls.CheckBox;
	import flash.display.DisplayObject;   
	import flash.text.TextField;
	public class CenterCheckBox extends CheckBox
	{

		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void   
		{   
			super.updateDisplayList(unscaledWidth, unscaledHeight);   
			var n:int = numChildren;   
			for(var i:int = 0; i < n; i++)   
			{   
				var c:DisplayObject = getChildAt(i);   
				if( !(c is TextField))   
				{   
					c.x = Math.round((unscaledWidth - c.width) / 2);   
					c.y = Math.round((unscaledHeight - c.height) /2 );   
				}   
			}   
		}   
	}
}