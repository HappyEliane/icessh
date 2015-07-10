// ActionScript file
package Ordersp{
	public class ReadWSDL
	{
	
		
		import mx.collections.ArrayCollection;
		import mx.controls.Alert;
		import mx.rpc.events.ResultEvent;
		import mx.rpc.http.HTTPService;
		
		import org.osmf.layout.AbsoluteLayoutFacet;
		
		
		private var Rwsdl:Boolean=true;
		
		private var wsname:String="";
		private var wspre:String="";
		private var urladd:String="";
		public  var invokeName:String="";
		
		
		public function ReadWsdl(url:String,name:String):void
		{
			// TODO Auto-generated method stub
			wsname=name;
			urladd=url;
		//	invokeName=iname;
			
			var httpservice:HTTPService=new HTTPService();
			httpservice.resultFormat="text";
			
			httpservice.url="http://localhost:8080/ServicePatternDesigner/flexServlet";
			httpservice.method="POST";
			var param:Object=new Object;
			param.kind="query";
			param.wsdl=url;
			
			httpservice.send(param);
			httpservice.addEventListener(ResultEvent.RESULT,success_handler);
		}
		import com.adobe.serialization.json.JSON;						  //JSON:1
		[Bindable]
		private var jsonObj:Object; 
		private function success_handler(result:ResultEvent):void
		{
			
			var re:String=result.result.toString();                        //JSON:3
			jsonObj=JSON.decode(re);                                      //JSON:4
			var r:ArrayCollection=new ArrayCollection(jsonObj as Array);  //JSON:5
			
			var s:String="";
			var wsdl:WSDL=new WSDL();
			wsdl.predix=wspre;
			wsdl.name=wsname;
			Alert.show("11"+invokeName);
			wsdl.invokename=invokeName;
			s+=("location:"+r.getItemAt(0).location+"\n");
			wsdl.location=r.getItemAt(0).location;
			s+=("TargetNamespace:"+r.getItemAt(0).targetName+"\n");
			wsdl.targetNamespace=r.getItemAt(0).targetName;
			s+=("wsdlNamespace:"+r.getItemAt(0).wsdlNamespace+"\n");
			wsdl.wsdlNamespace=r.getItemAt(0).wsdlNamespace;
			wsdl.service=r.getItemAt(0).serviceName;
			wsdl.title=r.getItemAt(0).title;
			var g:Object=r.getItemAt(0).portTypes;
			var g1:ArrayCollection=new ArrayCollection(g as Array);
		//	wsdl.operations=g1;
		//	s+=g1.length+"\n";
			//	showtext.text=re;
		//	for(var i:int=0;i<g1.length;i++)
			{
				s+="\n";
				s+=("portType:"+g1.getItemAt(0).portTypename+"\n");	
				s+=("operation:"+g1.getItemAt(0).operationName+"\n");
				s+=("inputMessage:"+g1.getItemAt(0).inputMessage+"\n");
				s+=("outputMessage:"+g1.getItemAt(0).outputMessage+"\n");
				s+=("inputParameter:"+g1.getItemAt(0).inputParameter+"\n");
				s+=("outputParameter:"+g1.getItemAt(0).outputParameter+"\n");
				s+="\n";
			}
			wsdl.portType=g1.getItemAt(0).portTypename;
			wsdl.operationName=g1.getItemAt(0).operationName;
			wsdl.inputmessage=g1.getItemAt(0).inputMessage;
			wsdl.outputmessage=g1.getItemAt(0).outputMessage;
			wsdl.inputParameter=g1.getItemAt(0).inputParameter;
			wsdl.outputParameter=g1.getItemAt(0).outputParameter;
			wsdl.inputElement=g1.getItemAt(0).inputElement;
			wsdl.outputElement=g1.getItemAt(0).outputElement;
			//	Alert.show(s);
			
			//	Alert.show(s);
			var url:String=wsdl.location;
			Flow.processDefine.allWsdl.addItem(wsdl);
			
			
			
		
			Rwsdl=true;
			Alert.show("导入成功！"+url,"提示");
			//	Alert.show(Flow.currentSelectedTab.processDefine.importWSDL.toString());
		}
	}
}