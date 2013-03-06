
package LocalPlayer
{
	import zillians.api.debug.*;
	import RemotePlayer.*;


	object PlayerObject
	{
		var posX:uint32;
		var posY:uint32;
		var ID:uint32;
	}

	object GlobalGameObject
	{
		var p1	:PlayerObject;
		var p2	:PlayerObject;
	}

	@handler { handle="SessionOpen"; }	
	function doSessionOpen():void
	{
		var game_object:GlobalGameObject;
		if( global == null ) {
			game_object = new GlobalGameObject;
			global = game_object;

			game_object . p1 = null;
			game_object . p2 = null;
		}
		game_object = cast( global, GlobalGameObject );

		if( game_object . p1 = null ) {
			game_object . p1 = new PlayerObject;
                        self = game_object . p1;
			registerID( 1 );
		} else if( game_object . p2 = null ) {
			game_object . p2 = new PlayerObject;
                        self = game_object . p2; 
			registerID( 2 );
		} else {
			puts(" user register nothing ");
		}

	}


	@handler { handle="SessionClose"; }
	function doSessionClose():void
	{
	}

	@server
	function tryMove( var x:uint32, var y:uint32):void
	{
	}

	@client
	function registerID( var ID:uint32 ):void;

	@server
	function changeAvatarID( var avatar_id:uint32 ):void
	{
	}
	
	@server
	function tryChangeDirection( var dir:uint32 ):void
	{
	}
}
