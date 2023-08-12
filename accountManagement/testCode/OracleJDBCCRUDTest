import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleJDBCCRUDTest {
    public static void main(String[] args) {
        // Oracle JDBC 드라이버 클래스 이름
        String driver = "oracle.jdbc.driver.OracleDriver";
        
        // Oracle 데이터베이스 URL,  접속IP, PORT, DB version 설정
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        
        // 데이터베이스 접속 정보
        String user = "your_username";     // DB 계정의 사용자명
        String password = "your_password"; // DB 계정의 비밀번호

        Connection connection = null;      // DB와 연결되어 연결 정보를 가지는 클래스
        PreparedStatement preparedStatement = null;  // 미리 컴파일된 쿼리를 나타내는 클래스, 쿼리에서 변수부분을 "?"와 같은 플레이스 홀더로 대체하여 작성하도록 도와줌 
        ResultSet resultSet = null;        // 쿼리 결과를 나타내는 클래스

        try {
            // JDBC 드라이버 로드
            Class.forName(driver);

            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);

            // 데이터 조회 쿼리 작성
            String selectQuery = "SELECT * FROM your_table_name";

            // PreparedStatement 생성
            preparedStatement = connection.prepareStatement(selectQuery);

            // 쿼리 실행, ResultSet 클래스로 쿼리 결과(투플)을 받는다.   
            resultSet = preparedStatement.executeQuery();

            // 결과 출력
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // 결과 처리 코드 작성
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // 데이터 추가 쿼리 작성
            String insertQuery = "INSERT INTO your_table_name (id, name) VALUES (?, ?)";  // 쿼리 변수부분 "?"로 대체
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 101);                           // 첫번째 ? 부분(id) 값 지정 
            preparedStatement.setString(2, "New Name");                 // 두번째 ? 부분(name) 값 지정
            int rowsInserted = preparedStatement.executeUpdate();       // 쿼리 실행
            System.out.println(rowsInserted + " row(s) inserted.");

            // 데이터 업데이트 쿼리 작성
            String updateQuery = "UPDATE your_table_name SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, "Updated Name"); // new name
            preparedStatement.setInt(2, 101); // id to update
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");

            // 데이터 삭제 쿼리 작성
            String deleteQuery = "DELETE FROM your_table_name WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, 101); // id to delete
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
