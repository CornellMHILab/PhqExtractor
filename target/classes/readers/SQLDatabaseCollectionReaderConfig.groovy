package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://<server>;databasename=DM_NLP;integratedSecurity=false"
String username="<user>";
String password="<paswd>";
String query = "SELECT ID, NOTE_TEXT FROM (SELECT ROW_NUMBER() OVER (ORDER BY (Select 1)) AS rowNum, CONCAT(PAT_ID, '|', PAT_ENC_CSN_ID, '|', NOTE_ID, '|', NOTE_CSN_ID, '|', NOTE_TYPE, '|', CONVERT(VARCHAR, CONTACT_DATE,121) ) AS ID, NOTE_TEXT FROM DM_NLP.dbo.DEPRESSION_PHQ_EVAL ) tab WHERE rowNum between 0 and 1001"

// String query = "SELECT CONCAT(PAT_ID, '|', PAT_ENC_CSN_ID, '|', NOTE_ID, '|', NOTE_CSN_ID, '|', NOTE_TYPE, '|', CONVERT(VARCHAR, CONTACT_DATE, 121) ) AS ID, NOTE_TEXT FROM DM_NLP.dbo.depression_phq_notes"
// String query = "SELECT  ID, NOTE_TEXT FROM DM_NLP.dbo.PHQ_NOTES where RowNo > 0 and RowNo < 100"

int startingIndex = 0;
int endingIndex = 10;
int batch_size = 10000;

reader = new BatchDatabaseCollectionReader(
        driver,
        url,
        username,
        password,
        query,
        "id","note_text",   /// Make sure that the names of the fields are low case.
        startingIndex , endingIndex
        , batch_size)
