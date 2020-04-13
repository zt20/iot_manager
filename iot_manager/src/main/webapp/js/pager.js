Pagination = function (options) {
    var _this = this;
    var _index = 1;
    var _size = 20;
    var _recordCount = 0;
    var _maxLinkCount = 5;

    this.setPageIndex = function (index) {
        _index = index;
    }

    this.setPageSize = function (size) {
        _size = size;
    }

    this.setRecordCount = function (recordCount) {
        _recordCount = recordCount;
    }

    this.getRecordCount = function () {
        return _recordCount;
    }

    this.getPageSize = function () {
        return _size;
    }

    this.getPageIndex = function () {

        if (_index > 0)
            return _index;
        else
            return 1;
    }

    this.getNextPageIndex = function () {
        if (_this.getPageIndex() < _this.getPageCount())
            return parseInt(_this.getPageIndex()) + 1;
        else
            return _this.getPageIndex();
    }

    this.getPrevPageIndex = function () {
        if (_this.getPageIndex() > 1)
            return parseInt(_this.getPageIndex()) - 1;
        else
            return _this.getPageIndex();
    }

    this.getPageCount = function () {

        if (_recordCount == 0)
            return 0;

        if (_recordCount <= _size)
            return 1;

        var _count = Math.floor(_recordCount / _size);

        if (_recordCount % _size > 0)
            _count = _count + 1;

        return _count;
    }

    this.getHtml = function () {

        var begin = 1;
        var end = begin + _maxLinkCount;

        var b = _this.getPageIndex() > (Math.floor(_maxLinkCount / 2)); //前面
        var e = _this.getPageIndex() > (parseInt(_this.getPageCount()) - (Math.floor(_maxLinkCount / 2))); //后面

        if (b) {
            begin = parseInt(_this.getPageIndex()) - (Math.floor(_maxLinkCount / 2));
            end = begin + _maxLinkCount;
        }
        if (e) {
            begin = parseInt(_this.getPageCount()) - _maxLinkCount + 1;
            end = begin + _maxLinkCount;
        }

        if (begin <= 0)
            begin = 1;

        if (end >= parseInt(_this.getPageCount()) + 1)
            end = parseInt(_this.getPageCount()) + 1;

        //如果页面少于
        if (_maxLinkCount >= _this.getPageCount()) {
            begin = 1;
            end = parseInt(_this.getPageCount()) + 1;
        }

        var html = "";

        if (_this.getPageCount() > 1) {

            if (_this.getPageCount() > _maxLinkCount) {
                if (_this.getPageIndex() > 1)
                    html += "<li><a href=\"javascript:\" data-index=\"" + 1 + "\">首页</a></li>";

                if (_this.getPageIndex() > 1)
                    html += "<li><a href=\"javascript:\" data-index=\"" + _this.getPrevPageIndex() + "\">上一页</a></li>";
            }

            for (var i = begin; i < end; i++) {
                if (i == _this.getPageIndex())
                    html += "<li class=\"active\"><a href=\"javascript:\" data-index=\"" + i + "\">" + i + "</a></li>";
                else
                    html += "<li><a href=\"javascript:\" data-index=\"" + i + "\">" + i + "</a></li>";
            }

            if (_this.getPageCount() > _maxLinkCount) {
                if (_this.getPageIndex() < _this.getPageCount())
                    html += "<li><a href=\"javascript:\" data-index=\"" + _this.getNextPageIndex() + "\">下一页</a></li>";

                if (_this.getPageIndex() < _this.getPageCount())
                    html += "<li><a href=\"javascript:\" data-index=\"" + _this.getPageCount() + "\">尾页</a></li>";
            }
            html += "<li><a href=\"javascript:\" data-index=\"" + _this.getPageCount() + "\">共" + _this.getPageCount() + "页/" + _this.getRecordCount() + "条</a></li>";
        }


        return html;
    }
}

    
    
    
   
