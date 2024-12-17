package com.moqi.library.dao;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.mapper.BookPoMapper;
import com.moqi.library.mapper.mapper.BookMapper;
import com.moqi.library.mapper.po.BookPoExample;
import com.moqi.library.mapper.po.BookPoWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDao {
    private final static Logger logger = LoggerFactory.getLogger(BookDao.class);
    private BookPoMapper bookPoMapper;
    private BookMapper bookMapper;

    @Autowired
    public BookDao(BookPoMapper bookPoMapper, BookMapper bookMapper){
        this.bookPoMapper = bookPoMapper;
        this.bookMapper = bookMapper;
    }

    /**
     * 创建Book对象
     *
     * @param book 传入的Book对象
     * @return 返回对象ReturnObj
     * @throws BusinessException 抛出错误
     */
    public Book createBook(Book book) throws BusinessException {
        BookPoWithBLOBs po = bookMapper.bookToBookPoWithBLOBs(book);
        bookPoMapper.insertSelective(po);
        return bookMapper.bookPoWithBLOBsToBook(po);
    }

    /**
     * 删除图书
     *
     * @param id 图书id
     */
    public void deleteBook(Long id) throws BusinessException{
        int ret = bookPoMapper.deleteByPrimaryKey(id);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BOOK_DELETE_FAILED);
        }
    }

    /**
     * 修改图书信息
     *
     * @param book 传入的book对象
     */
    public Book modifyBook(Book book) throws BusinessException {
        book.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        BookPoWithBLOBs po = bookMapper.bookToBookPoWithBLOBs(book);
        int ret = bookPoMapper.updateByPrimaryKeySelective(po);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BOOK_NOT_FOUND);
        }
        return bookMapper.bookPoWithBLOBsToBook(po);
    }

    /**
     * 根据ID查询图书信息
     *
     * @param id 图书id
     * @return 返回查询到的Book对象
     * @throws BusinessException 抛出错误
     */
    public Book getBookById(Long id) throws BusinessException {
        BookPoWithBLOBs po = bookPoMapper.selectByPrimaryKey(id);
        if (po == null) {
            throw new BusinessException(ReturnNo.BOOK_NOT_FOUND, "图书不存在");
        }
        return bookMapper.bookPoWithBLOBsToBook(po);
    }

    /**
     * 根据名字查询图书（模糊）
     *
     * @param name 图书名称的关键词
     * @return 返回匹配的Book对象的列表
     * @throws BusinessException 抛出错误
     */
    public List<Book> getBooksByName(String name) throws BusinessException {
        BookPoExample example = new BookPoExample();
        example.createCriteria().andBookNameLike("%" + name + "%");
        List<BookPoWithBLOBs> poWithBLOBsList = bookPoMapper.selectByExampleWithBLOBs(example);
        return poWithBLOBsList.stream()
                .map(bookMapper::bookPoWithBLOBsToBook)
                .collect(Collectors.toList());
    }

    /**
     * 查询所有书籍信息
     *
     * @return 所有书籍列表
     * @throws BusinessException 抛出错误
     */
    public List<Book> getAllBooks() throws BusinessException {
        List<BookPoWithBLOBs> poWithBLOBsList = bookPoMapper.selectByExampleWithBLOBs(null);
        return poWithBLOBsList.stream()
                .map(bookMapper::bookPoWithBLOBsToBook)
                .collect(Collectors.toList());
    }
}
