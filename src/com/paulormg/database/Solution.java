package com.paulormg.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

	public static final String DELIMITER = "----";

	static class Row {

		int id;
		Integer[] columns;

		public Row(int id, int columnCount){
			this.id = id;
			this.columns = new Integer[columnCount];
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Row other = (Row) obj;
			if (id != other.id)
				return false;
			return true;
		}

		public String toString(LinkedList<Integer> colsToReturn) {
			StringBuilder builder = new StringBuilder();
			
			for (Iterator<Integer> iterator = colsToReturn.iterator(); iterator
					.hasNext();) {
				Integer colIndex = iterator.next();
				builder.append(this.columns[colIndex]);
				if (iterator.hasNext()) {
					builder.append(",");
				}
			}
			
			return builder.toString();
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < columns.length; i++) {
				builder.append(this.columns[i]);				
				if (i != columns.length - 1){
					builder.append(",");
				}
			}
			return builder.toString();
		}

		
	}

	static class Database {

		private TreeMap<Integer, Row>[] rowsIndexedByColumnData;
		int rowCount;

		@SuppressWarnings("unchecked")
		public Database(int columnCount){
			this.rowCount = 0;
			this.rowsIndexedByColumnData = new TreeMap[columnCount];
			for (int i = 0; i < columnCount; i++) {
				this.rowsIndexedByColumnData[i] = new TreeMap<Integer, Row>();
			}
		}

		public void addRow(String[] strRow){
			Row newRow = new Row(rowCount++, strRow.length);
			for (int i = 0; i < strRow.length; i++) {
				Integer data = Integer.valueOf(strRow[i]);
				newRow.columns[i] = data;
				rowsIndexedByColumnData[i].put(data, newRow);
			}
		}

		public List<Row> execute(Query query){

			HashSet<Row> resultSet = new HashSet<Row>();

			switch (query.conj){

			case Query.CONJ_AND:
				resultSet.addAll(singleQuery(query.exprs.removeFirst()));
				for (Expression expr : query.exprs) {
					resultSet.retainAll(singleQuery(expr));
				}
				break;
			case Query.CONJ_OR:
				for (Expression expr : query.exprs) {
					resultSet.addAll(singleQuery(expr));				
				}
				break;
			default:
				resultSet.addAll(singleQuery(query.exprs.getFirst()));
				break;
			}
			
			
			// Sort result
			ArrayList<Row> result = new ArrayList<Row>(resultSet);
			Collections.sort(result, new Comparator<Row>() {

				@Override
				public int compare(Row o1, Row o2) {
					for (int i = 0; i < o1.columns.length; i++) {
						int diff = o1.columns[i] - o2.columns[i];
						if (diff != 0) return diff;
					}
					return 0;
				}
			});
			
			return result;
		}

		private Collection<Row> singleQuery(Expression expr) {

			// TODO: Initial version. Needs optimization 
			// (ex: perform sub-queries on AND conjunctions).
			
			// TODO: Filter by column
			
			switch (expr.op){
			case Query.OP_GREATER:
				return rowsIndexedByColumnData[expr.colIndex].tailMap(expr.literal, false).values();
			case Query.OP_LESS:
				return rowsIndexedByColumnData[expr.colIndex].headMap(expr.literal, false).values();
			case Query.OP_DIFF:
				Map<Integer, Row> diff = new TreeMap<Integer, Row>(rowsIndexedByColumnData[expr.colIndex]);
				diff.remove(expr.literal);
				return diff.values();
			default: //OP_EQ
				ArrayList<Row> eq = new ArrayList<Row>(1);
				eq.add(rowsIndexedByColumnData[expr.colIndex].get(expr.literal));
				return eq;
			}
		}
	}

	static class Expression {
		int colIndex;
		char op;
		int literal;

		public Expression(int index, char op, int literal) {
			this.colIndex = index;
			this.op = op;
			this.literal = literal;
		}
	}
	
	static class Query {

		public static final char SEPARATOR = '#';
		public static final char COL_SEPARATOR = ',';

		public static final char CONJ_AND = '&';
		public static final char CONJ_OR = '|';
		public static final char CONJ_NONE = 'a';

		public static final char OP_EQ = '=';
		public static final char OP_DIFF = '!';
		public static final char OP_GREATER = '>';
		public static final char OP_LESS = '<';

		char conj = CONJ_NONE;
		LinkedList<Integer> colsToReturn = new LinkedList<Integer>();
		LinkedList<Expression> exprs = new LinkedList<Expression>();

		static Query parseQuery(String strQuery){
			Query result = new Query();

			String[] returnExps = strQuery.split("\\" + SEPARATOR);

			String[] colsToReturn = returnExps[0].split("\\" + COL_SEPARATOR);
			for (String colToReturn : colsToReturn) {
				result.colsToReturn.add(Integer.valueOf(colToReturn));
			}

			String[] exps = returnExps[1].split("\\" + CONJ_AND);
			if (exps.length > 1){
				result.conj = CONJ_AND;
			} else {
				exps = returnExps[1].split("\\" + CONJ_OR);
				if (exps.length > 1){
					result.conj = CONJ_OR;					
				}
			}

			for (String strExp : exps) {
				result.exprs.add(parseExpression(strExp));
			}

			return result;
		}

		private static Expression parseExpression(String strExp) {
			String[] indexAndLiteral;
			char op;

			if (strExp.contains("" + OP_DIFF)){
				op = OP_DIFF;
				indexAndLiteral = strExp.trim().split("\\" + OP_DIFF);
			} else if (strExp.contains("" + OP_GREATER)){
				op = OP_GREATER;
				indexAndLiteral = strExp.trim().split("\\" + OP_GREATER);
			} else if (strExp.contains("" + OP_LESS)) { 
				op = OP_LESS;
				indexAndLiteral = strExp.trim().split("\\" + OP_LESS);
			} else { //OP_EQ
				op = OP_EQ;
				indexAndLiteral = strExp.trim().split("\\" + OP_EQ);
			}

			return new Expression(Integer.valueOf(indexAndLiteral[0]), op, Integer.valueOf(indexAndLiteral[1]));
		}

	}

	private static String query(Database db, String line) {
		StringBuilder builder = new StringBuilder();
		
		Query query = Query.parseQuery(line);
		
		List<Row> queryRows = db.execute(query);
				
		for (Row row : queryRows) {
			builder.append(row.toString(query.colsToReturn));
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Database db = null;

		boolean insertion = true;

		String line;
		String[] row;
		while (sc.hasNextLine()){
			line = sc.nextLine();

			if (line.trim().equals(DELIMITER)){
				insertion = false;
				continue;
			}

			if (insertion){
				row = line.trim().split("\\s+");
				
				if (db == null) db = new Database(row.length);
				
				db.addRow(row);
			} else {
				String result = query(db, line);
				System.out.print(result);
			}
		}

		sc.close();
	}

}