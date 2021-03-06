package vn.team.freechat.test.game;

import static vn.team.freechat.test.game.GameCommands.FIRE;

import com.tvd12.ezyfox.bean.annotation.EzyPrototype;
import com.tvd12.ezyfox.core.annotation.EzyClientRequestListener;
import com.tvd12.ezyfox.core.exception.EzyBadRequestException;

import lombok.Setter;
import vn.team.freechat.handler.ChatClientRequestHandler;

@Setter
@EzyPrototype
@EzyClientRequestListener(FIRE)
public class GameFireHandler 
		extends ChatClientRequestHandler {

	private VirtualWorld virtualWorld = VirtualWorld.getInstance();

	@Override
	protected void execute() throws EzyBadRequestException {
		String username = user.getName();
		VirtualRoom room = virtualWorld.getRoom(username);
		Bullet bullet = room.addBullet();
		response(bullet);
	}
	
	private void response(Bullet bullet) {
		responseFactory.newObjectResponse()
			.command(FIRE)
			.session(session)
			.data(bullet)
			.execute();
	}
	
}
