package vadya_zakusylo.kickstarter.my_sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import vadya_zakusylo.kickstarter.model.dao.ProjectDao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProjectDaoMySql extends ProjectDao {
	private Connection connection;

	public ProjectDaoMySql(Connection connection) {
		this.connection = connection;
	}

	@Override
	public double getCurrenMoney(String nameProject) {
		double currentMoney = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(selectCurrentMoney());
			preparedStatement.setString(1, nameProject);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				currentMoney = resultSet.getDouble("project.currentMoney");
			}
		} catch (SQLException e) {
			System.out.println("Can't connect to table \"Projects\"");
		}
		return currentMoney;
	}

	private String selectCurrentMoney() {
		StringBuilder sql = new StringBuilder();
		sql.append("select currentMoney ");
		sql.append("from project ");
		sql.append("where project = ?;");
		return sql.toString();
	}

	@Override
	public void setCurrentMoney(double money, String nameProject) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(updateCurrentMoney());
			preparedStatement.setDouble(1, money);
			preparedStatement.setString(2, nameProject);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Transaction isn't successful");
		}
	}

	private String updateCurrentMoney() {
		StringBuilder sql = new StringBuilder();
		sql.append("update project ");
		sql.append("set currentMoney = ? ");
		sql.append("where project = ?;");
		return sql.toString();
	}

	@Override
	public void setQuestion(String question, String name) {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement(updateQuestion());
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problems with connect. Try again.");
		}
	}

	private String updateQuestion() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into questions ");
		sql.append("(question, id_project)");
		sql.append("values (?, ");
		sql.append("(select id_project");
		sql.append("from project ");
		sql.append("where project = ?));");
		return sql.toString();
	}
}
