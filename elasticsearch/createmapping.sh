curl -XPUT 'http://127.0.0.1:9200/myindex' -d '{ "mappings": {   "blogpost": {   "properties": {     "comments": {       "type": "neies": {         "name":    { "type": "string"  },          "comment": { "type": "string"  },           "age":     { "type": "short"   }, "stars":   { "type": "short"   },"date":    { "type": "date"    }}}}}}}'