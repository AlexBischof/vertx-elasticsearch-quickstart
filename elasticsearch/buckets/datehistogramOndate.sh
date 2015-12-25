curl -XPOST 'http://127.0.0.1:9200/myindex/_search?pretty' -d '{"size" : 0, "aggs" : {"age" : {"nested" : {"path" : "comments"},"aggs" : { "min_age" : { "date_histogram" : { "field" : "comments.date", "interval": "year" ,"format" : "yyyy-MM-dd", "order" : { "_key" : "desc" }} }}}}}'