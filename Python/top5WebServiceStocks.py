#!/usr/bin/python
import json
from bson import json_util
import bottle
from bottle import route, run, request, abort
import datetime
from pymongo import MongoClient

# set the connection to 'localhost' with the
# database 'market' and use the 'stocks' collection
connection = MongoClient('localhost', 27017)
db = connection['market']
collection = db['stocks']

# set up URI paths for REST service
# create a method that gets query by industry
# and then reads top 5 documents 
@route('/topstocks', method='GET')
def get_topstocks():
  industry=request.query.industry
  findStr={"Industry":industry}
  return read_document(findStr)
        
def read_document(document):
  try:
    print(document)
    result=collection.find(document,{"Ticker":1,"Industry":2,"Shares Outstanding":3,"50-Day Simple Moving Average":4,"Sector":5,"_id":0}).limit(5)
    print(list(result))
    return (json_util.dumps(list(result)))

  # handle exceptions
  except Exception as ve:
    abort(400, str(ve))
    return False
  else:
    return True

if __name__ == '__main__':
  #app.run(debug=True)
  run(host='localhost', port=8080)
