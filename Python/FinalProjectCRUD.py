#!/usr/bin/python
import json
from bson import json_util
import bottle
from bottle import route, run, request, abort
from pymongo import MongoClient
#import pdb; pdb.set_trace()

# set the connection to 'localhost' with the
# database 'market' and use the 'stocks' collection
connection = MongoClient('localhost', 27017)
db = connection['market']
collection = db['stocks']

# setup URI paths for REST service.
@route('/create', method='POST')
def addDocument():
  try:
    # read in json and use save() function to add to database
    # read in json and use save() function to add to database
    strings = request.json
    myDocument = {"Ticker": strings["Ticker"], "Company": strings["Company"]}
    print(myDocument)
    result = collection.save(myDocument)
    print("Created successfully")
			
    #myDocument = {"Ticker": strings["Ticker"], "Company": strings["Company"]}
    #myDocument = {"Ticker": strings["Ticker"]}
    #print(myDocument)
    #result = collection.save(myDocument)
    #print("Created successfully")

  # handle error conditions
  except Exception as ve:
    abort(400, str(ve))
    print("Create Error: ", str(ve))

# setup URI paths for REST service.
@route('/read', method='GET')
def readDocument():
  # read json and create data to submit
  ticker=request.query.ticker
  findStr={"Ticker":ticker}
  try:
    # use find() function to read document from collection
    result=collection.find(findStr)
    return(json_util.dumps(list(result)))
  # handle error conditions
  except Exception as ve:
    abort(400, str(ve))
    return False
  else:
    return True

# setup URI paths for REST service.
@route('/update', method='GET')
def updateDocument():
  # read json, create data to submit
  ticker=request.query.ticker
  change=request.query.change
  findJson={"Ticker":ticker}        # this is data to find in collection
  updateJson={"Change":change}      # once document is found, this is data to update
  try:
    # use the update() function to update the document
    result=collection.update(findJson,{"$set":updateJson})
    print(json_util.dumps(list(result)))
  # hande error conditions
  except ValidationError as ve:
    abort(400, str(ve))
    print 'Handline exception:', ve
    return False

# setup URI paths for REST service.
@route('/delete', method='GET')
def deleteDocument():
  # read json, create data to submit
  ticker=request.query.ticker
  findJson={"Ticker":ticker}
  try:
    # use the remove() function to delete the document
    result=collection.remove(findJson)
    print(json_util.dumps(list(result)))
  # handle error conditions
  except ValidationError as ve:
    abort(400, str(ve))
    print 'Handling exception: ', ve

if __name__ == '__main__':
  #app.run(debug=True)
  run(host='localhost', port=8080)
