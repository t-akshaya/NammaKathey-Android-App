package com.nammakathe.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nammakathe.core.data.local.entity.EarnedBadgeEntity;
import com.nammakathe.core.data.local.entity.QuizResultEntity;
import com.nammakathe.core.data.local.entity.UserProfileEntity;
import com.nammakathe.core.data.local.entity.UserProgressEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProgressDao_Impl implements UserProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuizResultEntity> __insertionAdapterOfQuizResultEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavorite;

  private final EntityUpsertionAdapter<UserProgressEntity> __upsertionAdapterOfUserProgressEntity;

  private final EntityUpsertionAdapter<EarnedBadgeEntity> __upsertionAdapterOfEarnedBadgeEntity;

  private final EntityUpsertionAdapter<UserProfileEntity> __upsertionAdapterOfUserProfileEntity;

  public UserProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuizResultEntity = new EntityInsertionAdapter<QuizResultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `quiz_results` (`id`,`heroId`,`userId`,`score`,`totalQuestions`,`timeTakenSeconds`,`takenAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuizResultEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getHeroId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getScore());
        statement.bindLong(5, entity.getTotalQuestions());
        statement.bindLong(6, entity.getTimeTakenSeconds());
        statement.bindLong(7, entity.getTakenAt());
      }
    };
    this.__preparedStmtOfUpdateFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE user_progress SET isFavorite = ? WHERE heroId = ? AND userId = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfUserProgressEntity = new EntityUpsertionAdapter<UserProgressEntity>(new EntityInsertionAdapter<UserProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `user_progress` (`heroId`,`userId`,`lastPageRead`,`isCompleted`,`isFavorite`,`quizScore`,`readingTimeSeconds`,`lastReadAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProgressEntity entity) {
        statement.bindString(1, entity.getHeroId());
        statement.bindString(2, entity.getUserId());
        statement.bindLong(3, entity.getLastPageRead());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getQuizScore());
        statement.bindLong(7, entity.getReadingTimeSeconds());
        statement.bindLong(8, entity.getLastReadAt());
      }
    }, new EntityDeletionOrUpdateAdapter<UserProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `user_progress` SET `heroId` = ?,`userId` = ?,`lastPageRead` = ?,`isCompleted` = ?,`isFavorite` = ?,`quizScore` = ?,`readingTimeSeconds` = ?,`lastReadAt` = ? WHERE `heroId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProgressEntity entity) {
        statement.bindString(1, entity.getHeroId());
        statement.bindString(2, entity.getUserId());
        statement.bindLong(3, entity.getLastPageRead());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getQuizScore());
        statement.bindLong(7, entity.getReadingTimeSeconds());
        statement.bindLong(8, entity.getLastReadAt());
        statement.bindString(9, entity.getHeroId());
      }
    });
    this.__upsertionAdapterOfEarnedBadgeEntity = new EntityUpsertionAdapter<EarnedBadgeEntity>(new EntityInsertionAdapter<EarnedBadgeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `earned_badges` (`badgeId`,`userId`,`heroId`,`earnedAt`,`score`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EarnedBadgeEntity entity) {
        statement.bindString(1, entity.getBadgeId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getHeroId());
        statement.bindLong(4, entity.getEarnedAt());
        statement.bindLong(5, entity.getScore());
      }
    }, new EntityDeletionOrUpdateAdapter<EarnedBadgeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `earned_badges` SET `badgeId` = ?,`userId` = ?,`heroId` = ?,`earnedAt` = ?,`score` = ? WHERE `badgeId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EarnedBadgeEntity entity) {
        statement.bindString(1, entity.getBadgeId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getHeroId());
        statement.bindLong(4, entity.getEarnedAt());
        statement.bindLong(5, entity.getScore());
        statement.bindString(6, entity.getBadgeId());
      }
    });
    this.__upsertionAdapterOfUserProfileEntity = new EntityUpsertionAdapter<UserProfileEntity>(new EntityInsertionAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `user_profile` (`id`,`name`,`email`,`ageGroup`,`isGuest`,`avatarIndex`,`createdAt`,`totalReadingMinutes`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getAgeGroup());
        final int _tmp = entity.isGuest() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getAvatarIndex());
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getTotalReadingMinutes());
      }
    }, new EntityDeletionOrUpdateAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `user_profile` SET `id` = ?,`name` = ?,`email` = ?,`ageGroup` = ?,`isGuest` = ?,`avatarIndex` = ?,`createdAt` = ?,`totalReadingMinutes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getAgeGroup());
        final int _tmp = entity.isGuest() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getAvatarIndex());
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getTotalReadingMinutes());
        statement.bindString(9, entity.getId());
      }
    });
  }

  @Override
  public Object insertQuizResult(final QuizResultEntity result,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuizResultEntity.insert(result);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFavorite(final String heroId, final String userId, final boolean isFavorite,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFavorite.acquire();
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, heroId);
        _argIndex = 3;
        _stmt.bindString(_argIndex, userId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertProgress(final UserProgressEntity progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfUserProgressEntity.upsert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertBadge(final EarnedBadgeEntity badge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfEarnedBadgeEntity.upsert(badge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertUserProfile(final UserProfileEntity profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfUserProfileEntity.upsert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UserProgressEntity>> getAllProgressForUser(final String userId) {
    final String _sql = "SELECT * FROM user_progress WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_progress"}, new Callable<List<UserProgressEntity>>() {
      @Override
      @NonNull
      public List<UserProgressEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHeroId = CursorUtil.getColumnIndexOrThrow(_cursor, "heroId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLastPageRead = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPageRead");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfQuizScore = CursorUtil.getColumnIndexOrThrow(_cursor, "quizScore");
          final int _cursorIndexOfReadingTimeSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "readingTimeSeconds");
          final int _cursorIndexOfLastReadAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadAt");
          final List<UserProgressEntity> _result = new ArrayList<UserProgressEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserProgressEntity _item;
            final String _tmpHeroId;
            _tmpHeroId = _cursor.getString(_cursorIndexOfHeroId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpLastPageRead;
            _tmpLastPageRead = _cursor.getInt(_cursorIndexOfLastPageRead);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final int _tmpQuizScore;
            _tmpQuizScore = _cursor.getInt(_cursorIndexOfQuizScore);
            final int _tmpReadingTimeSeconds;
            _tmpReadingTimeSeconds = _cursor.getInt(_cursorIndexOfReadingTimeSeconds);
            final long _tmpLastReadAt;
            _tmpLastReadAt = _cursor.getLong(_cursorIndexOfLastReadAt);
            _item = new UserProgressEntity(_tmpHeroId,_tmpUserId,_tmpLastPageRead,_tmpIsCompleted,_tmpIsFavorite,_tmpQuizScore,_tmpReadingTimeSeconds,_tmpLastReadAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getProgressForHero(final String heroId, final String userId,
      final Continuation<? super UserProgressEntity> $completion) {
    final String _sql = "SELECT * FROM user_progress WHERE heroId = ? AND userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, heroId);
    _argIndex = 2;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProgressEntity>() {
      @Override
      @Nullable
      public UserProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHeroId = CursorUtil.getColumnIndexOrThrow(_cursor, "heroId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLastPageRead = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPageRead");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfQuizScore = CursorUtil.getColumnIndexOrThrow(_cursor, "quizScore");
          final int _cursorIndexOfReadingTimeSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "readingTimeSeconds");
          final int _cursorIndexOfLastReadAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadAt");
          final UserProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpHeroId;
            _tmpHeroId = _cursor.getString(_cursorIndexOfHeroId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpLastPageRead;
            _tmpLastPageRead = _cursor.getInt(_cursorIndexOfLastPageRead);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final int _tmpQuizScore;
            _tmpQuizScore = _cursor.getInt(_cursorIndexOfQuizScore);
            final int _tmpReadingTimeSeconds;
            _tmpReadingTimeSeconds = _cursor.getInt(_cursorIndexOfReadingTimeSeconds);
            final long _tmpLastReadAt;
            _tmpLastReadAt = _cursor.getLong(_cursorIndexOfLastReadAt);
            _result = new UserProgressEntity(_tmpHeroId,_tmpUserId,_tmpLastPageRead,_tmpIsCompleted,_tmpIsFavorite,_tmpQuizScore,_tmpReadingTimeSeconds,_tmpLastReadAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getCompletedCount(final String userId) {
    final String _sql = "SELECT COUNT(*) FROM user_progress WHERE userId = ? AND isCompleted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_progress"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getFavoriteHeroIds(final String userId) {
    final String _sql = "SELECT heroId FROM user_progress WHERE userId = ? AND isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_progress"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<EarnedBadgeEntity>> getEarnedBadges(final String userId) {
    final String _sql = "SELECT * FROM earned_badges WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"earned_badges"}, new Callable<List<EarnedBadgeEntity>>() {
      @Override
      @NonNull
      public List<EarnedBadgeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBadgeId = CursorUtil.getColumnIndexOrThrow(_cursor, "badgeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfHeroId = CursorUtil.getColumnIndexOrThrow(_cursor, "heroId");
          final int _cursorIndexOfEarnedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "earnedAt");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final List<EarnedBadgeEntity> _result = new ArrayList<EarnedBadgeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EarnedBadgeEntity _item;
            final String _tmpBadgeId;
            _tmpBadgeId = _cursor.getString(_cursorIndexOfBadgeId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpHeroId;
            _tmpHeroId = _cursor.getString(_cursorIndexOfHeroId);
            final long _tmpEarnedAt;
            _tmpEarnedAt = _cursor.getLong(_cursorIndexOfEarnedAt);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            _item = new EarnedBadgeEntity(_tmpBadgeId,_tmpUserId,_tmpHeroId,_tmpEarnedAt,_tmpScore);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getBadgeCount(final String userId) {
    final String _sql = "SELECT COUNT(*) FROM earned_badges WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"earned_badges"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object hasBadge(final String badgeId, final String userId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM earned_badges WHERE badgeId = ? AND userId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, badgeId);
    _argIndex = 2;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProfileEntity> getUserProfile(final String userId) {
    final String _sql = "SELECT * FROM user_profile WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfAgeGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "ageGroup");
          final int _cursorIndexOfIsGuest = CursorUtil.getColumnIndexOrThrow(_cursor, "isGuest");
          final int _cursorIndexOfAvatarIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarIndex");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTotalReadingMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "totalReadingMinutes");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpAgeGroup;
            _tmpAgeGroup = _cursor.getString(_cursorIndexOfAgeGroup);
            final boolean _tmpIsGuest;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsGuest);
            _tmpIsGuest = _tmp != 0;
            final int _tmpAvatarIndex;
            _tmpAvatarIndex = _cursor.getInt(_cursorIndexOfAvatarIndex);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final int _tmpTotalReadingMinutes;
            _tmpTotalReadingMinutes = _cursor.getInt(_cursorIndexOfTotalReadingMinutes);
            _result = new UserProfileEntity(_tmpId,_tmpName,_tmpEmail,_tmpAgeGroup,_tmpIsGuest,_tmpAvatarIndex,_tmpCreatedAt,_tmpTotalReadingMinutes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<QuizResultEntity>> getQuizResultsForUser(final String userId) {
    final String _sql = "SELECT * FROM quiz_results WHERE userId = ? ORDER BY takenAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"quiz_results"}, new Callable<List<QuizResultEntity>>() {
      @Override
      @NonNull
      public List<QuizResultEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHeroId = CursorUtil.getColumnIndexOrThrow(_cursor, "heroId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfTotalQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalQuestions");
          final int _cursorIndexOfTimeTakenSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "timeTakenSeconds");
          final int _cursorIndexOfTakenAt = CursorUtil.getColumnIndexOrThrow(_cursor, "takenAt");
          final List<QuizResultEntity> _result = new ArrayList<QuizResultEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuizResultEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpHeroId;
            _tmpHeroId = _cursor.getString(_cursorIndexOfHeroId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final int _tmpTotalQuestions;
            _tmpTotalQuestions = _cursor.getInt(_cursorIndexOfTotalQuestions);
            final int _tmpTimeTakenSeconds;
            _tmpTimeTakenSeconds = _cursor.getInt(_cursorIndexOfTimeTakenSeconds);
            final long _tmpTakenAt;
            _tmpTakenAt = _cursor.getLong(_cursorIndexOfTakenAt);
            _item = new QuizResultEntity(_tmpId,_tmpHeroId,_tmpUserId,_tmpScore,_tmpTotalQuestions,_tmpTimeTakenSeconds,_tmpTakenAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Float> getAverageScore(final String userId) {
    final String _sql = "SELECT AVG(score * 100.0 / totalQuestions) FROM quiz_results WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"quiz_results"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getTotalReadingMinutes(final String userId) {
    final String _sql = "SELECT SUM(totalReadingMinutes) FROM user_profile WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
