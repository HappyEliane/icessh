package Ordersp {
	
	import mx.collections.ArrayCollection;

	public class ProcessDefine {
		public var name:String;
		
		
		
		
		
		
		
		
		public var invokeCount:int=0;
		public var assignCount:int=0;
		public var ifCount:int=0;
		//public var actImageMap:HashMap = new HashMap();
		public var importWSDL:ArrayCollection=new ArrayCollection();
		public var allWsdl:ArrayCollection=new ArrayCollection();
	//	public var Predix:ArrayCollection=new ArrayCollection();
	//	public var PartnerLink:ArrayCollection=new ArrayCollection();
		public var Variables:ArrayCollection=new ArrayCollection();
		public var invokes:ArrayCollection=new ArrayCollection();

		
		
		public var correlation:ArrayCollection=new ArrayCollection();
		
		public var title:String="";
		public var cname:String="";
		public var des:String="";
		public var provider:String="";
		public var field:String="";
		public var qos:String="时间:1:2,成本:2:3,价格:3:4";
		public var inputD:ArrayCollection=new ArrayCollection();
		public var outputD:ArrayCollection=new ArrayCollection();
		public function ProcessDefine(){
			
		}
		
	}
}