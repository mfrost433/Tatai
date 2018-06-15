package com.a03.multiplayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.a03.database.Connector;
import com.a03.game.MaoriNumbers;
import com.a03.recieveaudio.RecieveMultiplayerImpl;
import com.a03.recieveaudio.RecieveMultiplayerImplService;
import com.a03.speech.VoiceConverter;

public class Main {

	public static void main(String[] args) {
		String gameId = "5d4a055e-2d99-40e5-a447-d64ae76944ce";
		System.out.println(gameId);
		NextMultiplayerLevelImplService s = new NextMultiplayerLevelImplService();
		NextMultiplayerLevelImpl impl =  s.getNextMultiplayerLevelImplPort();
		System.out.println(impl.resumeGame(gameId, "0edc037a-862a-4a00-b441-30efc7f97cdb.1508625655349"));
	}
}
